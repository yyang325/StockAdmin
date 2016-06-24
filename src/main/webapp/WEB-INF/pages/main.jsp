<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="Stock Monitor">
    <meta name="author" content="Yi Yang">

    <title>Dashboard Template for Bootstrap</title>

    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/dashboard.css" rel="stylesheet">

  </head>

  <body ng-app="mainApp" ng-controller="mainCtrl">

    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
            <span class="sr-only">Toggle navigation</span>
          </button>
          <a class="navbar-brand" href="#">Monitor Stock Price</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li style="cursor: pointer"><a href="#">Dashboard</a></li>
            <li style="cursor: pointer"><a href="#">Help</a></li>
          </ul>
        </div>
      </div>
    </nav>

    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-10 col-sm-offset-1 col-md-10 col-md-offset-1 main">
          <div class="page-header col-md-12" style="vertical-align: bottom;">
          	<h1 class="col-md-4">Dashboard</h1>
          	<div class="input-group" style="top:25px;">
          	  
		      <input  type="text" class="form-control" ng-model="stockSymbol" placeholder="Stock Symbol">
		      <div class="input-group-btn">
		        <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Action <span class="caret"></span></button>
		        <ul class="dropdown-menu dropdown-menu-right">
		          <li style="cursor: pointer" ng-click="addStock()"><a>Add Stock</a></li>
		          <li style="cursor: pointer" ng-click="deleteStock()"><a>Delete Stock</a></li>
		        </ul>
		      </div><!-- /btn-group -->
		    </div><!-- /input-group -->
          </div>
			
          <h2 class="sub-header">Stock List</h2>
          <div class="table-responsive" style="height: 180px; overflow:scroll">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>Name</th>
                  <th>Symbol</th>
                  <th>Price</th>
                  <th>Change</th>
                  <th>Percent Change</th>
                </tr>
              </thead>
              <tbody>
                <tr ng-repeat = "stock in stocks" ng-click="showHistory(stock)">
                  <td>{{stock.name}}</td>
                  <td>{{stock.symbol}}</td>
                  <td ng-class="{greenFont: stock.change<0, redFont: stock.change>0}">{{stock.price}}</td>
                  <td ng-class="{greenFont: stock.change<0, redFont: stock.change>0}">{{stock.change}}</td>
                  <td ng-class="{greenFont: stock.change<0, redFont: stock.change>0}">{{stock.percentChange}}</td>
                </tr>
              </tbody>
            </table>
          </div>
          
          <h2>Stock History <span ng-if="stockNameHistory.length > 0"> -- </span>  <span>{{stockNameHistory}}</span>  <span>{{stockSymbolHistory}}</span>  </h2>
          <div class="row">
	          <div class="col-md-4">
	          	<canvas id="c" height="400px"></canvas>
	          </div>
	          <div class="col-md-7 col-md-offset-1" style="height: 400px; overflow: scroll;">
		         <table class="table table-striped">
	              <thead>
	                <tr>
	                  <th>Date</th>
	                  <th>Open</th>
	                  <th>High</th>
	                  <th>Low</th>
	                  <th>Close</th>
	                  <th>Volume</th>
	                  <th>Adj Close</th>
	                </tr>
	              </thead>
	              <tbody>
	                <tr ng-repeat = "record in stockHistory" class="small">
	                  <td>{{record[0]}}</td>
	                  <td>{{record[1]}}</td>
	                  <td>{{record[2]}}</td>
	                  <td>{{record[3]}}</td>
	                  <td>{{record[4]}}</td>
	                  <td>{{record[5]}}</td>
	                  <td>{{record[6]}}</td>
	                </tr>
	              </tbody>
	            </table>
	          </div>
          </div>
        </div>
      </div>
    </div>

    <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.9/angular.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/1.0.2/Chart.min.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/stock.js"></script>
  </body>
</html>
