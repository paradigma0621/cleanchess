module paradigma.cleanchess {

    requires chesslib;
    requires javafx.fxml;
	requires javafx.graphics;
    requires javafx.controls;

    opens paradigma.cleanchess to javafx.fxml;
    exports paradigma.cleanchess;
    exports paradigma.cleanchess.controller;
    exports paradigma.cleanchess.view;
    exports paradigma.cleanchess.model;
    
    opens paradigma.cleanchess.controller to javafx.fxml;
    exports paradigma.cleanchess.model.entity;
    exports paradigma.cleanchess.novos.model;
    exports paradigma.cleanchess.novos.view;
    opens paradigma.cleanchess.novos.view to javafx.fxml;

}
