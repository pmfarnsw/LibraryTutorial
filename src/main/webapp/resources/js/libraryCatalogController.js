//make library module for my controller to be used for controllers filters and directives
var libraryApp = angular.module('libraryApp', ['ngGrid','ui.bootstrap','ngAnimate', 'toaster']);



//create libraryApp controller
libraryApp.controller('libraryCtrl', function( $scope,$http,$modal,$log,toaster){
	
	$scope.editedCells = [];
	$scope.booksNotYetSaved = [];
	$scope.availablebooks = false;

	$scope.test = 'this is a test';
	
	$scope.getLibrary = function() {

			$http.get('ViewLibrary').success(function(result) {
				$scope.library = result;
				$scope.libraryMaster = result;
				
				

			});
			
	};
	$scope.filterOptions = {
		    filterText: ''
		  };
	
	$scope.gridOptions = { 
			data: 'library', 
		    enableCellSelection: true,
	        enableRowSelection: false,
	        enableCellEdit: true,
	        filterOptions: $scope.filterOptions,
	        columnDefs: [
	                     {field:'title', displayName:'Title', enableCellEdit: false},
	                     {field:'author', displayName:'Author', enableCellEdit: false},
	                     {field:'category', displayName:'Category', enableCellEdit: false},
	                     {field: 'publicationDate', displayName: 'Publication Date', enableCellEdit: false}, 
	                     {field:'isbn', displayName:'ISBN', enableCellEdit: false},
	                     {field:'deleteBook',displayName:'Delete Book',enableCellEdit: false, cellTemplate:"<button type=\"button\" class=\"btn btn-danger center\" ng-click=\"confirmDeletion(row)\"><span class=\"glyphicon glyphicon-remove text-center\" aria-hidden=\"true\"></span></button>"}]
	
	
	};
	
	$scope.confirmDeletion = function(book)
	{
		var flag = true;
		
		 if( confirm( "Are you sure you want to delete "+book.entity.title +" by "+ book.entity.author+" ?" ))
			 {
			 	
			 
			 	
			 	for(i=0;i<$scope.booksNotYetSaved.length;i++){
			 		
			 		if(book.entity.isbn==$scope.booksNotYetSaved[i].isbn)
			 			{
			 			
			 				$scope.booksNotYetSaved.splice(i,1);
			 				
			 				
			 				flag=false;
			 				
			 				
			 				break;
			 			}
			 	}
				for(i=0;i<$scope.library.length;i++){
							 		
							 		if(book.entity.isbn==$scope.library[i].isbn)
							 			{
							 				
							 				
							 				$scope.library.splice(i,1);
							 				
							 				
							 				
							 				
							 				
							 				break;
							 			}
							 	}
			 	
			 	if(flag){
			 		$scope.deleteBook(book.entity.isbn);
			 		
			 		
			 	}
			 	 else if($scope.booksNotYetSaved.length==0)
				{
			 		 
				  $scope.availablebooks=false;
				  				}
			 	toaster.pop('success', "Deletion", book.entity.title +" by "+ book.entity.author + " has been deleted.", null, true);
				   
                
			 }
	};
	
	$scope.removeUnsavedBook = function(isbn)
	{
		
	};
	
	$scope.deleteBook = function(isbn) {
		$http.put('remove/' + isbn).success(function(data) {
			$scope.getLibrary();
		});
	};
	
	$scope.addBook = function(book)
	{
		var flag = true;
		angular.forEach($scope.library,function(value,index){
			if(book.isbn == value.isbn)
				{
					flag = false;
					
				}
		})
		if(flag)
			{
				$scope.library.push(book);
				$scope.booksNotYetSaved.push(book);
				toaster.pop('success', "Added", 'Book has been successfully', null, true);
				$scope.availablebooks = true;
				
				
			} else
				{
					console.log("Already Exists");
					toaster.pop('error', "Error", 'A book with  same ISBN already Exists', null, true);
				}
		
		
	};
	
	$scope.saveBook = function() {
		books = $scope.booksNotYetSaved;
		
		var bookJSON = angular.toJson(books, 'false');
		
		
		$http.post('add/' + bookJSON).success(function(data) {
			console.log("Array is equal to: "+data);
			if(data.length<=0)
				{
				$scope.booksNotYetSaved = [];
				$scope.availablebooks = false;
				toaster.pop('success', "Saved", 'All added books have been saved successfully', null, true);
				$scope.getLibrary();

				}
			else 
				{
				toaster.pop('error','Error Adding Books','Was not able to add some books because an isbn already exists for them', null, true);
				$scope.booksNotYetSaved = data;
				}
				
			
			
		});
		
	};
	
	$scope.cacheRow = function(startValue) {
		  $scope.rowHolder = {};
		  angular.copy(startValue, $scope.rowHolder);
		  
		};
		
		
		//ng-grid validation
		$scope.validateCell = function(cell)
		{
			var title = $scope.validateString(cell.title, 'Title');
			var author = $scope.validateString(cell.author,'Author');
			var category = $scope.validateString(cell.category,'Category');
			var pubDate = $scope.validateDate(cell.publicationDate,"Publication Date");
			if(title && author && category && pubDate)
				return true;
			
			return false;
		}
		
		$scope.validateString = function( x , cellName)
		{
			
			if(x =="")
				{
					errors = '1';
					alert(cellName + " cannot be left blank.");
					return false;
				} 
			
			return true;
		}
		
		$scope.validateDate = function(date,cellName)
		{
			if(date.length != 4 || !angular.isNumber(date))
				{
					alert(cellName+ " can contain onlyNumbers and must be a valid year");
					return false;
				}
			return true;
		}
		
	
		
	$scope.addCellToBeUpdated = function(cell)
	{
		if($scope.editedCells.length == 0)
			{
				$scope.editedCells.push(cell);
			} 
		else 
			{
				flag = true;
				angular.forEach($scope.editedCells,function(value,index){
					if(cell.isbn == value.isbn)
						{
						
						value = cell;
						flag = false;
						return;
						}
				});
				
				if(flag)
				{
					$scope.editedCells.push(cell);
				}
			}
	}
	
	
	$scope.$on('ngGridEventStartCellEdit', function(evt){
		$scope.cellBeingEdited = evt.targetScope.row.entity;
	    $scope.cacheRow($scope.cellBeingEdited); 
	});

	
	$scope.$on('ngGridEventEndCellEdit', function(evt){
		afterEdit = evt.targetScope.row.entity;
		
		if(!angular.equals(afterEdit,$scope.rowHolder))
			{
				if($scope.validateCell(afterEdit))
					{
					$scope.addCellToBeUpdated(afterEdit);
					console.log(true);
					} 
				else{
					
					evt.targetScope.row.entity = $scope.rowHolder;
					//$scope.$apply();
				}
				
								
			}
			
	});

	
	//functions for the modal containing form to add new books
	  $scope.open = function (size) {

	    var modalInstance = $modal.open({
	      templateUrl: 'myModalContent.html',
	      controller: 'ModalInstanceCtrl',
	      size: size,
	      resolve: {
	    	  user: function () {
	    		
                return $scope.user;
            }
	      }
	    });

	    modalInstance.result.then(function (lib) {
	    
	     var book = {
	    		 title: lib.title,
	    		 author:lib.author,
	    		 publicationDate:lib.publicationDate,
	    		 category:lib.category,
	    		 isbn:lib.isbn
	     		
	     };
	    
	     $scope.addBook(book);
	    }, function () {
	    	
	      $log.info('Modal dismissed at: ' + new Date());
	    });
	  };
	

	
	
});




//the controller for the modal
libraryApp.controller('ModalInstanceCtrl', function ($scope, $modalInstance) {

$scope.years = [];
	
	$scope.populateYearSelector = function()
	{
		date = new Date().getFullYear();
		
		
		$scope.years.push(date);
		for(i=0 ;i <900;i++)
			{
				newDate = date - i;
				$scope.years.push(newDate);
				
			}
		
	}
	$scope.populateYearSelector();


	  $scope.submitForm = function()
	  {
		  
		  $modalInstance.close($scope);
		 
	  }
	
	  $scope.cancel = function () {
	    $modalInstance.dismiss('cancel');
	  };
	  
	  
	  
	  
	});
