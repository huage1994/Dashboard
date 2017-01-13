sap.ui.define([
	"sap/ui/core/mvc/Controller",
	"sap/ui/model/json/JSONModel",
	"sap/m/MessageToast"
], function(Controller, JSONModel, MessageToast) {
	"use strict";
	return Controller.extend("sap.ui.demo.controller.Detail", {

		onInit: function() {

			var oViewModel = new JSONModel({
				isEditable: false,
				message: "123",
				abort: "null",
				use: "null"
			});
			this.getView().setModel(oViewModel,"model1");
			this._model = oViewModel;
			var oRouter = sap.ui.core.UIComponent.getRouterFor(this);
			oRouter.getRoute("detail").attachPatternMatched(this._onObjectMatched, this);
		},
		_onObjectMatched: function(oEvent) {
			//alert(JSON.stringify(oEvent.getParameter("arguments")));
			//输出"assetpath :0"
			this.getView().bindElement({
				path: "/" + oEvent.getParameter("arguments").Assetpath,
				model: "asset"
			});
		},
		onPress: function(oEvent) {
			//alert(this._model.getProperty("message").toString());
			//MessageToast.show(this._model.getProperty("/message"));
			if (this._model.getProperty("/isEditable") === false) {
			

				this._model.setProperty("/isEditable", true);
			} else {
				this._model.setProperty("/isEditable", false);
				//this.getView().getModel("lifeCycle").setProperty(this.getView().byId("input1").getValue(),this.getView().byId("name").getText());
			
				MessageToast.show("modify success");

			}

		},
		submit: function(oEvent) {
			//MessageToast.show(this.getView().byId("name").getText());
			//MessageToast.show($("#input1").val());
			//MessageToast.show(this.getView().getModel().getProperty("/assetProductStatus"));
			/*$.get("./Asset.json",function(data,status){
				MessageToast.show("Data: " + data + "\nStatus: " + status);
			});*/
			MessageToast.show(this.getView().getModel().getJSON());

		}

	});
});