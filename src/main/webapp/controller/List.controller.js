sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/ui/model/json/JSONModel",
	"sap/ui/model/Filter",
	"sap/ui/model/FilterOperator"
], function(Controller, JSONModel, Filter, FilterOperator) {
	"use strict";

	return Controller.extend("sap.ui.demo.controller.List", {

		onInit: function() {

			/*	var modelTemp = new JSONModel();
				this.getView().setModel(modelTemp, "asset");

				$.get("./Asset.json", function(data) {
					modelTemp.setData(data);
					//alert(JSON.stringify(a));
				});*/
		/*	var oViewModel = new JSONModel({
				currency: "EUR"
			});*/
			
			var oModel = new JSONModel("./Node.json");
            this.getView().setModel(oModel);

			//alert(modelTemp.getJSON());
			//alert(JSON.stringify(a));

			/*this.getView().setModel(oViewModel, "view");*/
		},
		onFilterInvoices: function(oEvent) {

			// build filter array
			var aFilter = [];
			var sQuery = oEvent.getParameter("query");
			if (sQuery) {
				aFilter.push(new Filter("UserName", FilterOperator.Contains, sQuery));
			}

			// filter binding
			var oList = this.getView().byId("list");
			var oBinding = oList.getBinding("items");
			oBinding.filter(aFilter);
		},

		onPress: function(oEvent) {
			var oItem = oEvent.getSource();
			var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			var temp = oItem.getBindingContext("asset").getPath().substr(1);
			//temp = 0,1,2
			//alert(temp);
			oRouter.navTo("detail", {
				Assetpath: temp
			});
		}

	});
});