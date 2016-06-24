var app = angular.module('mainApp', []);

app.controller('mainCtrl', function($scope, $http, $interval){

	/*
	 * Method to add a specific stock, and update the list of stock by invoke $scope.showList()
	 * 
	 * */
	$scope.stocks = [];
	$scope.addStock = function(){
		if($scope.stockSymbol){
			$http({
				method:"POST",
				url: "api/stock/"+$scope.stockSymbol,
			}).success(function(data){
				$scope.stockSymbol = "";
				$scope.showList();
			});
		}
	}
	
	
	
	/*
	 * Method to delete a specific stock, and update the list of stock by invoke $scope.showList()
	 * 
	 * */
	$scope.deleteStock = function(){
		if($scope.stockSymbol){
			$http({
				method:"DELETE",
				url: "api/stock/delete/"+$scope.stockSymbol,
			}).success(function(data){
				$scope.stockSymbol = "";
				$scope.showList();
			});
		}
	}
	
	
	/*
	 * Method to get All added stock, and use $interval service to refresh data in every 1.5 seconds
	 * 
	 * */
	$scope.showList = function(){
		$http({
			method: "GET",
			url: "api/stock"
		}).success(function(data){
			$scope.stocks = data;
		});
	}
	$scope.showList();
	$interval($scope.showList, 1500);
	
	
	
	/*
	 * Method to get specific stock's history price data. Store them into $scope.stockHistory
	 * 
	 * */
	$scope.stockHistory = [];
	$scope.stockNameHistory = "";
	$scope.stockSymbolHistory = "";
	$scope.showHistory = function(stock){
		$http({
			method: "GET",
			url: "api/stock/history/" + stock.symbol
		}).success(function(data){
			$scope.stockHistory = [];
			$scope.stockNameHistory = stock.name;
			$scope.stockSymbolHistory = stock.symbol;
			for(var i = 1; i < data.records.length; i++){
				$scope.stockHistory.push(data.records[i].split(','));
			}
			generate($scope.stockHistory);
		});
	}
	
	
	/**
	 * Use chart.js to generate a line chart for analysis the history data of stock
	 **/
	var generate = function(data){
		console.log(data[40][0])
		var len = data.length;
		var base = Math.floor(len/12);
		
		//Set up HTML5 Canvas and catch history data
		var ctx = document.getElementById("c").getContext("2d");
		var lab = [];
		var val = [];
		for(var i = 11; i >= 0; i--){
			lab.push(data[base*i][0]);
			val.push(data[base*i][4]);
		}
	    var data = {
	      labels: lab,
	      datasets: [{
	        label: "Stock Price Chart",
	        fillColor: "rgba(220,220,220,0.2)",
	        strokeColor: "rgba(220,220,220,1)",
	        pointColor: "rgba(220,220,220,1)",
	        pointStrokeColor: "#fff",
	        pointHighlightFill: "#fff",
	        pointHighlightStroke: "rgba(220,220,220,1)",
	        data: val
	      }]
	    };
	    var MyNewChart = new Chart(ctx).Line(data);
	}
	
});