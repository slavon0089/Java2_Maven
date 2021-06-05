package lesson7.awesome_project;

import lesson7.awesome_project.view.IUserInterface;
import lesson7.awesome_project.view.UserInterface;

public class Main {

    public static void main(String[] args) {
        IUserInterface userInterface = new UserInterface();
        userInterface.showUI();
    }
}