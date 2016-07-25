/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sneakerbadge01;

import java.util.ArrayList;
import java.util.HashMap;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

/**
 *
 * @author pears
 */
public class MeritBadgeGridPane extends GridPane{
    private final Insets DEFAULTMARGIN = new Insets(5.0);
    
    private String meritBadge;
    private ArrayList<Scout> scouts = new ArrayList<>();
    private HashMap<Scout, ArrayList<CheckBox>> scoutCheckboxHash = new HashMap<>(); 
    private ArrayList<String> requirements = new ArrayList<>();
    
    public MeritBadgeGridPane() {
        super();
    }
    
    void setGrid(ArrayList<String> requirementsList) {
        this.setGridLinesVisible(true);
        
        for(String requirement : requirementsList) {
            requirements.add(requirement);
        }
        
        ColumnConstraints fnameColumn = new ColumnConstraints();
        fnameColumn.setMinWidth(25.0);
        this.getColumnConstraints().add(fnameColumn);
        
        ColumnConstraints lnameColumn = new ColumnConstraints();
        lnameColumn.setMinWidth(25.0);
        this.getColumnConstraints().add(lnameColumn);
        
        this.add(new Label("First Name"), 0, 0);
        this.add(new Label("Last Name"), 1, 0);
        
        // All Boxes box
        CheckBox allBox = new CheckBox();
        this.add(allBox, 2, 1);
        GridPane.setHalignment(allBox, HPos.CENTER);
        allBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue == true) {
                    System.out.println("Check All Boxes!!");
                    scoutCheckboxHash.forEach((k, cbArray) -> {
                        cbArray.stream().forEach((cb) -> {
                            cb.setSelected(true);
                        });
                    });
                } else {
                    System.out.println("Uncheck All Boxes!!");
                    scoutCheckboxHash.forEach((k, cbArray) -> {
                        cbArray.stream().forEach((cb) -> {
                            cb.setSelected(false);
                        });
                    });
                    
                }
            }
            
        });
        
        int i = 3;
        for(String requirement : requirementsList) {
            ColumnConstraints requirementCol = new ColumnConstraints();
            requirementCol.setMinWidth(25.0);
            this.getColumnConstraints().add(requirementCol);
            
            Label requirementLbl = new Label(requirement);
            this.add(requirementLbl, i, 0);
            GridPane.setHalignment(requirementLbl, HPos.CENTER);
            
            CheckBox rowBox = new CheckBox();
            this.add(rowBox, i, 1);
            GridPane.setHalignment(rowBox, HPos.CENTER);
            
            IntegerProperty rowIndex = new SimpleIntegerProperty();
            rowIndex.set(i - 3); //TODO: Fix this index!!
            rowBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
                @Override
                public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                    if(newValue == true) {
                        System.out.println("Check This Row!!");
                        scoutCheckboxHash.forEach((k, cbArray) -> {
                            cbArray.get(rowIndex.get()).setSelected(true);
                        });
                    } else {
                        System.out.println("Uncheck This Row!!");
                        scoutCheckboxHash.forEach((k, cbArray) -> {
                            cbArray.get(rowIndex.get()).setSelected(false);
                        });

                    }
                }

            });
            
            i++;
        }
        this.getChildren().stream().forEach((theNode) -> {
            GridPane.setMargin(theNode, DEFAULTMARGIN);
        });
    }
    
    void addScout(Scout scout) {
        scouts.add(scout); // Must go before rowIndex declaration
        scoutCheckboxHash.put(scout, new ArrayList<>());
        Integer rowIndex = scouts.size() + 1;
        
        Label fname = new Label(scout.getFname());
        GridPane.setMargin(fname, DEFAULTMARGIN);
        Label lname = new Label(scout.getLname());
        GridPane.setMargin(lname, DEFAULTMARGIN);
        
        this.add(fname, 0, rowIndex);
        this.add(lname, 1, rowIndex);
        
        CheckBox scoutBox = new CheckBox();
        this.add(scoutBox, 2, rowIndex);
        GridPane.setHalignment(scoutBox, HPos.CENTER);
        
        scoutBox.selectedProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
                if(newValue == true) {
                    System.out.println("Check This Scout!!");
                    scoutCheckboxHash.get(scout).forEach((cb) -> {
                        cb.setSelected(true);
                    });
                } else {
                    System.out.println("Uncheck This Scout!!");
                    scoutCheckboxHash.get(scout).forEach((cb) -> {
                        cb.setSelected(false);
                    });
                }
            }

        });
        
        for(int i = 0; i < requirements.size(); i++) {
            CheckBox newCB = new CheckBox();
            this.add(newCB, i + 3, rowIndex);
            GridPane.setHalignment(newCB, HPos.CENTER);
            GridPane.setMargin(newCB, DEFAULTMARGIN);
            scoutCheckboxHash.get(scout).add(newCB);
        }
    }
}
