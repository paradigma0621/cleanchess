module groupId {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.base;
    
    requires ictk;
	requires javafx.graphics;
    
    opens paradigma.cleanchess to javafx.fxml;
    exports paradigma.cleanchess;
    exports paradigma.cleanchess.controller;
    exports paradigma.cleanchess.view;
    exports paradigma.cleanchess.model;
    
    opens paradigma.cleanchess.controller to javafx.fxml;
    exports paradigma.cleanchess.model.entity;

}
