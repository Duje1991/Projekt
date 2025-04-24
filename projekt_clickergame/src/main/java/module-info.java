module at.spengergasse.projekt_clickergame {
    requires javafx.controls;
    requires javafx.fxml;


    opens at.spengergasse.projekt_clickergame to game.fxml;
    exports at.spengergasse.projekt_clickergame;
}