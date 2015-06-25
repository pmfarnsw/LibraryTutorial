<%@ page language="java" contentType="text/html; charset=US-ASCII"
	pageEncoding="US-ASCII"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html ng-app="libraryApp">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>National Park Library Catalog</title>
<link rel="stylesheet" type="text/css"
	href="/mylibrary/resources/css/ng-grid.min.css" />

<link rel="stylesheet" href="/mylibrary/resources/css/bootstrap.min.css">

	<link rel="stylesheet/less" type="text/css" href="/mylibrary/resources/css/library-catalog-styles.less"/>
<script src="/mylibrary/resources/js/jquery-1.11.2.js"></script>


<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.3.14/angular.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/less.js/2.5.0/less.min.js" type="text/javascript"></script>
<script type="text/javascript"
	src="/mylibrary/resources/js/ng-grid-2.0.14.min.js"></script>

<script src="/mylibrary/resources/js/ui-bootstrap-tpls-0.12.1.min.js"></script>
<script type="text/javascript"
	src="/mylibrary/resources/js/libraryCatalogController.js"></script>

<link
	href="https://cdnjs.cloudflare.com/ajax/libs/angularjs-toaster/0.4.9/toaster.min.css"
	rel="stylesheet" />

<script src="/mylibrary/resources/js/angular-animate.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/angularjs-toaster/0.4.9/toaster.min.js"></script>



</head>
<body ng-controller="libraryCtrl" ng-init="getLibrary()">

	<header>

	<h1 id="libraryName">National Park Library</h1>
	<span class="glyphicons glyphicons"></span> <img id="libraryImg"
		src="http://placehold.it/100x100"></img> </header>
	<div class="nav">

		<ul>
			<li><a href="HomePage">Home</a></li>

			<li><a href="LibraryCatalog" class="active">View Catalog</a></li>
		</ul>
	</div>
	<div class="container">
		<toaster-container
			toaster-options="{'time-out': 3000, 'close-button':true, 'animation-class': 'toast-top-center'}"></toaster-container>
		<strong>Search:</strong>
		</string>
		<input type="text" ng-model="filterOptions.filterText" />
		<button class="btn btn-success pull-right" ng-click="open()">Add
			Book</button>
		<button class="btn btn-success pull-right"
			ng-disabled="!availablebooks" ng-click="saveBook()">Save
			Changes</button>
		<div class="gridStyle" ng-grid="gridOptions"></div>
		<div class="space"></div>
	</div>
	<script type="text/ng-template" id="myModalContent.html">

        <div class="modal-header">
            <h3 class="modal-title">Add Book</h3>
        </div><form name="form.libForm" ng-submit="submitForm()" novalidate>
    <div class="modal-body">
        <!-- NAME -->
        <div class="form-group">
            <label>Title*
				<span ng-show="form.libForm.title.$invalid && !form.libForm.title.$pristine" class="help-block">Please enter in a title</span>
				<span ng-show="form.libForm.title.$valid && !form.libForm.title.$pristine" class="help-block glyphicon glyphicon-ok" aria-hidden="true"></span>	
			</label>
            <input placeholder="Title of Book"  type="text" name="title" class="form-control" ng-model="title" required>

        </div>
		 <div class="form-group">
            <label>Author*
				<span ng-show="form.libForm.author.$invalid && !form.libForm.author.$pristine" class="help-block">Please enter in authors name</span>
				<span ng-show="form.libForm.author.$valid && !form.libForm.author.$pristine" class="help-block glyphicon glyphicon-ok" aria-hidden="true"></span>	
			</label>
            <input placeholder="Author of Book" type="text" name="author" class="form-control" ng-model="author" required>
            
        </div>
		<div class="form-group">
			<label>Publication Date*
				<span ng-show="form.libForm.publicationDate.$valid && !form.libForm.publicationDate.$pristine" class="help-block glyphicon glyphicon-ok" aria-hidden="true"></span>	
			</label>

			 <select class="form-control" ng-model="publicationDate" name="publicationDate" ng-options=" year for year in years" required></select>
				
		</div>
		 <div class="form-group">
            <label>Category*
				<span ng-show="form.libForm.category.$invalid && !form.libForm.category.$pristine" class="help-block">Please enter in a category</span>
				<span ng-show="form.libForm.category.$valid && !form.libForm.category.$pristine" class="help-block glyphicon glyphicon-ok" aria-hidden="true"></span>	
			</label>
            <input  placeholder="Book Genre" type="text"  name="category" class="form-control" ng-model="category" required>
            
        </div>
		 <div class="form-group">
            <label>Isbn* 
				<span ng-show="form.libForm.isbn.$invalid && !form.libForm.isbn.$pristine" class="help-block">Please enter in numerical book isbn</span>
				<span ng-show="form.libForm.isbn.$valid && !form.libForm.isbn.$pristine" class="help-block glyphicon glyphicon-ok" aria-hidden="true"></span>	
			</label>
            <input placeholder="Books ISBN" type="text" ng-pattern="/^[0-9]*$/" name="isbn" class="form-control" ng-model="isbn" required>
           
        </div>
 
       
 
    </div>
    <div class="modal-footer">
        <button type="submit" class="btn btn-primary" ng-disabled="form.libForm.$invalid">Add Book</button>
        <button class="btn btn-warning" ng-click="cancel()">Cancel</button>
    </div>
</form>    
</script>

	<footer>
	<div class="info text-center">Privacy Policy | User Rights |
		Accessability | Language Translations | Social Media | Country Code</div>
	<div class="limited text-center">National Park Library Limited</div>
	</footer>
</body>
</html>