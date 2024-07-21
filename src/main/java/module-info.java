module groupId {

    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires javafx.base;
    
    requires ictk;
	requires javafx.graphics;
    
    opens paradigma0621.cleanchess to javafx.fxml;
    exports paradigma0621.cleanchess;
    exports paradigma0621.cleanchess.controller;
    exports paradigma0621.cleanchess.view;
    exports paradigma0621.cleanchess.model;
    
  /*  opens groupId.artifactId.model to javafx.fxml;
    opens groupId.artifactId.view to javafx.fxml;
    */
    opens paradigma0621.cleanchess.controller to javafx.fxml;
  
}
