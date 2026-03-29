module paradigma0621.cleanchess {

    requires chesslib;
    requires javafx.fxml;
	requires javafx.graphics;
    requires javafx.controls;
    requires javafx.swing;
    requires static lombok;

    opens paradigma0621.cleanchess to javafx.fxml;
    exports paradigma0621.cleanchess;
    exports paradigma0621.cleanchess.controller;
    exports paradigma0621.cleanchess.view;
    exports paradigma0621.cleanchess.model;
    
    opens paradigma0621.cleanchess.controller to javafx.fxml;
    exports paradigma0621.cleanchess.model.entity;
    exports paradigma0621.cleanchess.novos.model;
    exports paradigma0621.cleanchess.novos.view;
    opens paradigma0621.cleanchess.novos.view to javafx.fxml;

}
