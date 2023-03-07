import java.util.Vector;

import java.awt.event.*;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import travelling_salesman.City;
import travelling_salesman.Route;
import travelling_salesman.TravellingSalesMan;

public class Driver extends JFrame implements ListSelectionListener{
    static JList<String> list;
    static DefaultListModel<String> model;
    static DefaultListModel<String> bestRouteModel;
    static Route bestRoute;
    
    JList<String> routeList;
    JScrollPane scroller;
    JScrollPane routeScroller;
    JPanel buttonContainer;

    JButton calculateButton;
    JButton resetButton;

    JLabel resultsLabel;

    public Driver(){
        super();
        initUI();
    }

    private void initUI(){
        this.setTitle("Travellingsalesman");
        this.resultsLabel = new JLabel("Best route found: ");
        
        this.buildlist();
        this.buildRouteList();
        setUpRadioButton();
    
        scroller = new JScrollPane(Driver.list);
        routeScroller = new JScrollPane(this.routeList);

    
        scroller.setBounds(0, 0, 250, 400);
        routeScroller.setBounds(254, 220, 400, 140);
        this.resultsLabel.setBounds(254, 180, 400, 40);
        this.add(scroller);
        this.add(resultsLabel);
        this.add(routeScroller);
        this.add(this.buttonContainer);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(800, 400);
        this.setLayout(null);
        //this.setVisible(true);

    }

    public void buildlist(){
        Driver.model = new DefaultListModel<String>(); 
        for (City city : City.cities) {
             model.addElement(city.getName());
        }
        Driver.list = new JList<>(model);

        Driver.list.addListSelectionListener(this);

    }

    public void buildRouteList(){
        Driver.bestRouteModel = new DefaultListModel<String>(); 
        if(this.bestRoute != null){
            for (City city : this.bestRoute.getRoute()) {
                bestRouteModel.addElement(city.getName());
            }
        }
        this.routeList = new JList<>(bestRouteModel);
    }

    
    public void updateRouteList(){
        Driver.bestRouteModel = new DefaultListModel<String>(); 
        if(Driver.bestRoute != null){
            for (City city : Driver.bestRoute.getRoute()) {
                bestRouteModel.addElement(city.getName());
            }
        }
        this.routeList.setModel(bestRouteModel);
        this.resultsLabel.setText("The best route found takes: "+ this.bestRoute.getDistance() + "km");
    }

    public void setUpRadioButton(){
        JRadioButton radioButtoon1 = new JRadioButton("Default");
        JRadioButton radioButtoon2 = new JRadioButton("Full (Jamaica)");
        JRadioButton radioButtoon3 = new JRadioButton("Full (US)");

        ButtonGroup buttonGroup11 = new ButtonGroup();

        this.buttonContainer = new JPanel();
        this.buttonContainer.setBounds(404, 0, 400, 110);
        this.buttonContainer.add(radioButtoon1);
        this.buttonContainer.add(radioButtoon2);
        this.buttonContainer.add(radioButtoon3);


        buttonGroup11.add(radioButtoon1);
        buttonGroup11.add(radioButtoon2);
        buttonGroup11.add(radioButtoon3);

        radioButtoon1.setSelected(true);

        ActionListener buttonListener = new ActionListener(){
            public void actionPerformed(ActionEvent actionEvent){
                AbstractButton aButton = (AbstractButton) actionEvent.getSource();
                if(aButton.getText() == "Default"){
                    City.loadCities("Default");
                }else if(aButton.getText() == "Full (Jamaica)"){
                    City.loadCities("Full (Jamaica)");
                    
                }else{
                    City.loadCities("Full (US)");
                }
                model.removeAllElements();
                for (City city : City.cities) {
                    model.addElement(city.getName());
                }
                Driver.list.setModel(model);
            }
        };
        radioButtoon1.addActionListener(buttonListener);
        radioButtoon2.addActionListener(buttonListener);
        radioButtoon3.addActionListener(buttonListener);
        
    }
    

    public void updateGui(){

    }

    public static void main(String[] args) throws Exception {
        
        TravellingSalesMan tsp = new TravellingSalesMan();
        City.loadCities("Default");

        EventQueue.invokeLater(() -> {
            var gui = new Driver();
            gui.setVisible(true);

            Route best = tsp.solve(City.cities, 3);
            Driver.bestRoute = best;
            gui.updateRouteList();
        });


    }

    @Override
    public void valueChanged(ListSelectionEvent arg0){
        if (!arg0.getValueIsAdjusting()) {
            TravellingSalesMan tsp = new TravellingSalesMan();
            int index = Driver.list.getSelectedIndex();

            Route best = tsp.solve(City.cities, index);
            Driver.bestRoute = best;
            this.updateRouteList();
        }
    }

    public static JList<String> getList() {
        return list;
    }

    public static void setList(JList<String> list) {
        Driver.list = list;
    }

    public JList<String> getRouteList() {
        return routeList;
    }

    public void setRouteList(JList<String> routeList) {
        this.routeList = routeList;
    }

    public static DefaultListModel<String> getModel() {
        return model;
    }

    public static void setModel(DefaultListModel<String> model) {
        Driver.model = model;
    }

    public static DefaultListModel<String> getBestRouteModel() {
        return bestRouteModel;
    }

    public static void setBestRouteModel(DefaultListModel<String> bestRouteModel) {
        Driver.bestRouteModel = bestRouteModel;
    }

    public JScrollPane getScroller() {
        return scroller;
    }

    public void setScroller(JScrollPane scroller) {
        this.scroller = scroller;
    }

    public JScrollPane getRouteScroller() {
        return routeScroller;
    }

    public void setRouteScroller(JScrollPane routeScroller) {
        this.routeScroller = routeScroller;
    }

    public JPanel getButtonContainer() {
        return buttonContainer;
    }

    public void setButtonContainer(JPanel buttonContainer) {
        this.buttonContainer = buttonContainer;
    }

    public JButton getCalculateButton() {
        return calculateButton;
    }

    public void setCalculateButton(JButton calculateButton) {
        this.calculateButton = calculateButton;
    }

    public JButton getResetButton() {
        return resetButton;
    }

    public void setResetButton(JButton resetButton) {
        this.resetButton = resetButton;
    }
}
