module groupId {

    //requires javafx.controls;
    requires javafx.fxml;
    //requires javafx.media;
    //requires javafx.base;
    
	requires javafx.graphics;
    requires javafx.controls;
    requires chesslib.RELEASE;

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
