package task1;

import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;


class Frame extends JFrame implements ActionListener{
    //Initialise Everything
    private JFrame idFrame;

    private JPanel idEnterTop, idButtonsMid, idListBot;

    private JTextField idInput, redIn, greIn, bluIn;
    private JTextArea idDisplay;

    private JLabel idListLabel, idInputLabel, statDis, colLab, redLab, bluLab, greLab;

    private JButton idAdd, idRem, idDis, idEra,	colApp, colEra;

    private ArrayList<Integer> idList = new ArrayList<Integer>();

    ID id = new ID();

    public Frame() {
        // Set Frame
        idFrame = new JFrame("ID Input");

        // Set Panels
        idEnterTop = new JPanel();
        idButtonsMid = new JPanel();
        idListBot = new JPanel();

        // Text Field and Area
        idInput = new JTextField();
        idDisplay = new JTextArea();

        redIn = new JTextField("0");
        greIn = new JTextField("0");
        bluIn = new JTextField("0");

        // Labels
        idListLabel = new JLabel("ID List");
        idInputLabel = new JLabel("Enter ID Here");
        statDis = new JLabel();

        colLab = new JLabel("Select Colour");
        redLab = new JLabel("Red");
        bluLab = new JLabel("Blue");
        greLab = new JLabel("Green");

        // Buttons
        idAdd = new JButton("Add ID");
        idRem = new JButton("Remove ID");
        idDis = new JButton("Display List");
        idEra = new JButton("Erase List");
        colApp = new JButton("Set Colour");
        colEra = new JButton("Remove Colour");

        idInputLabel.setBounds(50, 20, 95, 30);
        idInput.setBounds(50, 50, 280, 30);

        idAdd.setBounds(50, 100, 95, 30);
        idAdd.addActionListener(this);

        idRem.setBounds(150, 100, 95, 30);
        idRem.addActionListener(this);

        idDis.setBounds(250, 100, 100, 30);
        idDis.addActionListener(this);

        idEra.setBounds(355, 100, 95, 30);
        idEra.addActionListener(this);

        idListLabel.setBounds(50, 140, 95, 30);
        idDisplay.setBounds(50, 170, 280, 150);

        redLab.setBounds(340, 170, 50, 20);
        redIn.setBounds(340, 190, 50, 20);
        greLab.setBounds(340, 220, 50, 20);
        greIn.setBounds(340, 240, 50, 20);
        bluLab.setBounds(340, 270,50,20);
        bluIn.setBounds(340, 290, 50, 20);

        colApp.setBounds(400, 240, 95, 20);
        colApp.addActionListener(this);
        colEra.setBounds(400, 290, 120, 20);
        colEra.addActionListener(this);

        idFrame.add(idInputLabel);
        idFrame.add(idInput);
        idFrame.add(idAdd);
        idFrame.add(idRem);
        idFrame.add(idDis);
        idFrame.add(idEra);
        idFrame.add(idListLabel);
        idFrame.add(idDisplay);
        idFrame.add(redIn);
        idFrame.add(greIn);
        idFrame.add(bluIn);
        idFrame.add(colLab);
        idFrame.add(redLab);
        idFrame.add(bluLab);
        idFrame.add(greLab);
        idFrame.add(colApp);
        idFrame.add(colEra);

        idFrame.setSize(600, 400);
        idFrame.setLayout(null);
        idFrame.setVisible(true);
        idFrame.setResizable(false);
    }
    //Button Events
    @Override
    public void actionPerformed(ActionEvent event) {
        //Add ID
        if (event.getSource() == idAdd) {
            try {
                id.setID(Integer.parseInt(idInput.getText()));
                String idStr = idInput.getText();
                if (id.checkIDLength(idStr) == false) {
                    System.out.println("Error: ID cannot be added to the list");
                    statDisplay(102);
                } else {
                    idList.add(Integer.parseInt(idStr));
                    System.out.println(idStr+ " has been added to "+idList);
                    statDisplay(101);
                    //idDisplay.setText(idStr);
                    idInput.setText(null);
                    idDisplay.setText(null);
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: The ID you specified should not have letters in it");
                statDisplay(103);
            } catch (Exception e) {
                System.out.println("Error");
                statDisplay(104);
                e.printStackTrace();
            }

        }
        // Remove an ID
        if (event.getSource() == idRem) {
            try {
                id.setID(Integer.parseInt(idInput.getText()));
                String idStr = idInput.getText();
                int idInt = Integer.parseInt(idStr);
                Iterator<Integer> it = idList.iterator();
                while (it.hasNext()) {
                    int i = it.next();
                    if (i == idInt) {
                        it.remove();
                        System.out.println("ID "+idStr+ " has been removed "+idList);
                        statDisplay(201);
                        idDisplay.setText(null);
                    }
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: A Value Must Be Entered");
                statDisplay(202);
            } catch (Exception e) {
                System.out.println("Error");
                statDisplay(104);
            }
        }
        //Display IDs
        if (event.getSource() == idDis) {
            idDisplay.setText(null);
            displayList();
        }
        // Erase all the IDs in the List
        if (event.getSource() == idEra) {
            int option = JOptionPane.showConfirmDialog(this,"Are You Sure You "
                    + "Want To Erase All The ID's In The List?", "WARNING!", JOptionPane.YES_NO_OPTION);
            switch (option) {
                case 0:
                    idList.clear();
                    System.out.println("List Elements Deleted. Array Size: "+idList.size());
                    idDisplay.setText(null);
                    statDisplay(301);
                    break;
                case 1:
                    System.out.println("No Changes Were Made");
                    break;
            }
        }
        // Change the colours of the IDs
        if (event.getSource() == colApp) {
            try {
                int red = Integer.parseInt(redIn.getText());
                setColours(red);
                int green = Integer.parseInt(greIn.getText());
                setColours(green);
                int blue = Integer.parseInt(bluIn.getText());
                setColours(blue);

                if ((red == -1) || (blue == -1) || (green == -1)) {
                    idInput.setBackground(Color.BLACK);
                }
                else {
                    Color colour = new Color(red, green, blue);
                    idDisplay.setForeground(colour);
                }
            }

            catch (Exception e){
                idDisplay.setForeground(Color.BLACK);
            }

        }
        //Reset Colours
        if (event.getSource() == colEra) {
            redIn.setText("0");
            greIn.setText("0");
            bluIn.setText("0");

        }
    }
    // For Loop that displays and orders the IDs
    public void displayList() {
        Collections.sort(idList);
        for (int i : idList) {
            idDisplay.append(i +"\n");
        }

    }
    // Display Status Message for the ID Numbers
    private void statDisplay(int errorNum) {
        String statusStr = null;
        switch(errorNum) {
            case 101:
                statDis.setVisible(false);
                statusStr = "ID '" +id.getID()+"' has been added Successfully";
                statDis.setForeground(Color.GREEN);
                break;
            case 102:
                statDis.setVisible(false);
                statusStr = "Error: The ID '"+idInput.getText()+"' must be 6 Digits";
                statDis.setForeground(Color.RED);
                break;
            case 103:
                statDis.setVisible(false);
                statusStr = "Error: ID '"+idInput.getText()+"' must not contain Letters";
                statDis.setForeground(Color.RED);
                break;
            case 104:
                statDis.setVisible(false);
                statusStr = "Error: Unknown";
                statDis.setForeground(Color.RED);
                break;
            case 201:
                statDis.setVisible(false);
                statusStr = "ID '" +id.getID()+"' was Removed Successfully";
                statDis.setForeground(Color.GREEN);
                break;
            case 202:
                statDis.setVisible(false);
                statusStr = "Error: A Value Must Be Entered";
                statDis.setForeground(Color.RED);
                break;
            case 301:
                statDis.setVisible(false);
                statusStr = "List Cleared Successfully";
                statDis.setForeground(Color.GREEN);
                break;
        }
        statDis.setText(statusStr);
        idFrame.add(statDis);
        statDis.setBounds(50, 320, 400, 30);
        statDis.setVisible(true);
    }

    public int setColours(int a) {
        if ((a < -1) || (a > 256)) {
            a = -1;
            return a;
        }
        else {
            return a;
        }
    }
}

