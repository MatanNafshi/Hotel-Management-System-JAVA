package View;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
import java.awt.*;
import javax.swing.*;

import Exceptions.MaxPopulationCapacityException;
import Exceptions.PersonAlreadyExistException;
import model.Booking;
import model.Customer;
import model.Department;
import model.DepartmentManager;
import model.Employee;
import model.Hotel;
import model.Room;
import model.StandardRoom;
import model.Suite;
import model.SuperiorRoom;
import model.VIPCustomer;
import utils.Area;
import utils.Gender;
import utils.Specialization;


public class MainDashboard {
    @SuppressWarnings("unused")
	private boolean isAdmin; // New instance variable
    
	
	// Declare menu items as instance variables
	private JMenuItem addFunctionsMenu;
    private JMenuItem addEmployeeMenuItem;
    private JMenuItem addCustomerMenuItem;
    private JMenuItem addBookingMenuItem;
    private JMenuItem addRoomMenuItem;
    private JMenuItem addDepartmentMenuItem;
    //private JMenuItem setDepartmentManagerMenuItem;

    private JMenuItem removeFunctionsMenu;
    private JMenuItem removeEmployeeMenuItem;
    private JMenuItem removeCustomerMenuItem;
    private JMenuItem removeBookingMenuItem;
    private JMenuItem removeRoomMenuItem;
    private JMenuItem removeDepartmentMenuItem;

    private JMenuItem getRealFunctionsMenu;
    private JMenuItem getRealEmployee;
    private JMenuItem getRealCustomer;
    private JMenuItem getRealBooking;
    private JMenuItem getRealRoom;
    private JMenuItem getRealDepartment;

    private JMenuItem querieMenu;
    private JMenuItem kemployeesMenuItem;
    private JMenuItem allBookingsByRevenue;
    private JMenuItem allBookingsOfSpecCustomerMenuItem;
    private JMenuItem allCustomersByBookings;
    private JMenuItem allCustomersByPK;
    private JMenuItem totalProfitMenuItem;
    private JMenuItem customerBookedMostRoomsMenuItem;
    
	
    private JFrame frame;
    @SuppressWarnings("unused")
	private Hotel hotel;
    private LoginScreen loginScreen;

    public MainDashboard(String username, boolean isAdmin) {
    	this.isAdmin = isAdmin;
        this.loginScreen = LoginScreen.getInstance();
        hotel = Hotel.getInstance();
        loginScreen = LoginScreen.getInstance();
        
        //Initializing the JMenuItems to invisible, after we chack if this is an admin or employee,
        //they will be visible based on your role.
        addFunctionsMenu = new JMenuItem("Add Functions");
        addFunctionsMenu.setVisible(false);
              
        removeFunctionsMenu = new JMenuItem("Remove Functions");
        removeFunctionsMenu.setVisible(false);
        
        getRealFunctionsMenu = new JMenuItem("Get Real");
        getRealFunctionsMenu.setVisible(false);
        
        querieMenu = new JMenuItem("Queries");
        querieMenu.setVisible(false);
        
        
        addEmployeeMenuItem = new JMenuItem("Add Employee");
        addEmployeeMenuItem.setVisible(false);

        addCustomerMenuItem = new JMenuItem("Add Customer");
        addCustomerMenuItem.setVisible(false);

        addBookingMenuItem = new JMenuItem("Add Booking");
        addBookingMenuItem.setVisible(false);

        addRoomMenuItem = new JMenuItem("Add Room");
        addRoomMenuItem.setVisible(false);

        addDepartmentMenuItem = new JMenuItem("Add Department");
        addDepartmentMenuItem.setVisible(false);

        /*setDepartmentManagerMenuItem = new JMenuItem("Set Department Manager");
        setDepartmentManagerMenuItem.setVisible(false);*/

        // Initialize remove menu items and set their initial visibility to false
        removeEmployeeMenuItem = new JMenuItem("Remove Employee");
        removeEmployeeMenuItem.setVisible(false);

        removeCustomerMenuItem = new JMenuItem("Remove Customer");
        removeCustomerMenuItem.setVisible(false);

        removeBookingMenuItem = new JMenuItem("Remove Booking");
        removeBookingMenuItem.setVisible(false);

        removeRoomMenuItem = new JMenuItem("Remove Room");
        removeRoomMenuItem.setVisible(false);

        removeDepartmentMenuItem = new JMenuItem("Remove Department");
        removeDepartmentMenuItem.setVisible(false);

        // Initialize getReal menu items and set their initial visibility to false
        getRealEmployee = new JMenuItem("Get Real Employee");
        getRealEmployee.setVisible(false);

        getRealCustomer = new JMenuItem("Get Real Customer");
        getRealCustomer.setVisible(false);

        getRealBooking = new JMenuItem("Get Real Booking");
        getRealBooking.setVisible(false);

        getRealRoom = new JMenuItem("Get Real Room");
        getRealRoom.setVisible(false);

        getRealDepartment = new JMenuItem("Get Real Department");
        getRealDepartment.setVisible(false);

        // Initialize querie menu items and set their initial visibility to false
        kemployeesMenuItem = new JMenuItem("KEmployees");
        kemployeesMenuItem.setVisible(false);

        allBookingsByRevenue = new JMenuItem("All Bookings By Revenue");
        allBookingsByRevenue.setVisible(false);

        allBookingsOfSpecCustomerMenuItem = new JMenuItem("All Bookings of Specific Customer");
        allBookingsOfSpecCustomerMenuItem.setVisible(false);

        allCustomersByBookings = new JMenuItem("All Customers By Bookings");
        allCustomersByBookings.setVisible(false);

        allCustomersByPK = new JMenuItem("All Customers By Primary Key");
        allCustomersByPK.setVisible(false);
        
        customerBookedMostRoomsMenuItem = new JMenuItem("Customer Booked The Most Rooms");
        customerBookedMostRoomsMenuItem.setVisible(false);

        totalProfitMenuItem = new JMenuItem("Total Profit");
        totalProfitMenuItem.setVisible(false);
        
        // Create and set up the frame
        frame = new JFrame("Hotel Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon imageIcon = new ImageIcon(getClass().getResource("Screenshot 2023-07-23 104619.png"));
        frame.setIconImage(imageIcon.getImage());
        

        // Set up the background image
        ImageIcon backgroundImage = new ImageIcon(getClass().getResource("Screenshot 2023-07-23 104619.png"));
        @SuppressWarnings("serial")
		JLabel backgroundLabel = new JLabel(backgroundImage) {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(600, 400); // Set the size of the background image
            }
        };
        
        frame.setContentPane(backgroundLabel);
        frame.setLayout(new BorderLayout()); // Use BorderLayout for the content pane
        frame.setResizable(false);

        // Create and set up the menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        // Add Functions menu
        JMenu addFunctionsMenu = new JMenu("Add Functions");
        menuBar.add(addFunctionsMenu);
        
        // Add Remove Functions menu
        JMenu removeFunctionsMenu = new JMenu("Remove Functions");
        menuBar.add(removeFunctionsMenu);
        
        JMenu getRealFunctionsMenu = new JMenu("Get Real Functions");
        menuBar.add(getRealFunctionsMenu);
        
        JMenu querieMenu = new JMenu("Queries Functions");
        menuBar.add(querieMenu);

        /**********************************/
        // ADD OPTIONS
        
        // Add Customer option
        JMenuItem addCustomerMenuItem = new JMenuItem("Add Customer");
        addCustomerMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog for customer registration
                JPanel customerPanel = new JPanel(new GridLayout(0, 2, 5, 5));

                //Fields for customer
                JTextField idField = new JTextField();
                JTextField firstNameField = new JTextField();
                JTextField lastNameField = new JTextField();
                JTextField phoneNumberField = new JTextField();

                // Create and add other input fields for customer registration
                JComboBox<Area> areaComboBox = new JComboBox<>(Area.values());
                JComboBox<Gender> genderComboBox = new JComboBox<>(Gender.values());

                Integer[] birthYears = new Integer[66]; // Years from 1940 to 2005
                for (int i = 0; i < 66; i++) {
                    birthYears[i] = 1940 + i;
                }
                JComboBox<Integer> birthYearComboBox = new JComboBox<>(birthYears);

                JCheckBox vipCheckBox = new JCheckBox("VIP Customer");
                JLabel discountLabel = new JLabel("Discount Percentage:");
                JTextField discountField = new JTextField();

                // Define action for VIP checkbox
                vipCheckBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        discountLabel.setVisible(vipCheckBox.isSelected());
                        discountField.setVisible(vipCheckBox.isSelected());
                    }
                });

                // Date of joining components
                Integer[] daysArray = new Integer[31];
                for (int i = 0; i < 31; i++) {
                    daysArray[i] = i + 1;
                }
                JComboBox<Integer> daysComboBox = new JComboBox<>(daysArray);

                String[] monthsArray = new String[]{
                        "January", "February", "March", "April", "May", "June", "July",
                        "August", "September", "October", "November", "December"
                };
                JComboBox<String> monthsComboBox = new JComboBox<>(monthsArray);

                Integer[] yearsArray = new Integer[34];
                for (int i = 0; i < 34; i++) {
                    yearsArray[i] = 1990 + i;
                }
                JComboBox<Integer> yearsComboBox = new JComboBox<>(yearsArray);

                birthYearComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedBirthYear = (Integer) birthYearComboBox.getSelectedItem();

                        // Calculate the minimum year for the date of joining (16 years after the birth year)
                        int minJoinYear = selectedBirthYear + 16;
                        //I don't want a 5 year old customer to book rooms.

                        // Create an array of years from the minimum year to 2023
                        Integer[] updatedYearsArray = new Integer[2023 - minJoinYear + 1];
                        for (int i = 0; i <= 2023 - minJoinYear; i++) {
                            updatedYearsArray[i] = minJoinYear + i;
                        }

                        // Update yearsComboBox with the updatedYearsArray
                        DefaultComboBoxModel<Integer> yearsModel = new DefaultComboBoxModel<>(updatedYearsArray);
                        yearsComboBox.setModel(yearsModel);
                    }
                });

                customerPanel.add(new JLabel("Customer ID:"));
                customerPanel.add(idField);
                customerPanel.add(new JLabel("First Name:"));
                customerPanel.add(firstNameField);
                customerPanel.add(new JLabel("Last Name:"));
                customerPanel.add(lastNameField);
                customerPanel.add(new JLabel("Phone Number:"));
                customerPanel.add(phoneNumberField);
                customerPanel.add(new JLabel("Area:"));
                customerPanel.add(areaComboBox);
                customerPanel.add(new JLabel("Gender:"));
                customerPanel.add(genderComboBox);
                customerPanel.add(new JLabel("Year of Birth:"));
                customerPanel.add(birthYearComboBox);

                customerPanel.add(new JLabel("Date of Joining:"));
                JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
                datePanel.add(daysComboBox);
                datePanel.add(monthsComboBox);
                datePanel.add(yearsComboBox);
                customerPanel.add(datePanel);

                customerPanel.add(vipCheckBox);
                customerPanel.add(discountLabel);
                customerPanel.add(discountField);
                discountLabel.setVisible(false);
                discountField.setVisible(false);

                do {
                    int option = JOptionPane.showConfirmDialog(frame, customerPanel, "Add Customer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (option == JOptionPane.OK_OPTION) {
                        String id = idField.getText().trim();
                        String firstName = firstNameField.getText().trim();
                        String lastName = lastNameField.getText().trim();
                        String phoneNumber = phoneNumberField.getText().trim();

                        // Check if any required fields are empty
                        if (id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty()) {
                            showErrorMessage("Please fill in all the required fields.");
                            continue;
                        }

                        // Check if the ID is within the valid range
                        try {
                            Set<Integer> employeeIds = new HashSet<>();
                            int customerId = Integer.parseInt(id);
                            for (Employee employee : Hotel.getInstance().getAllEmployees().values()) {
                                employeeIds.add(Integer.parseInt(employee.getId()));
                            }
                            if (customerId < 100000000 || customerId > 999999999) {
                                showErrorMessage("Invalid ID! The ID must be a 9-digit number.");
                                continue;
                            } else if (employeeIds.contains(customerId)) {
                                showErrorMessage("There is an employee with the same ID!");
                                continue;
                            }
                        } catch (NumberFormatException ex) {
                            showErrorMessage("Invalid ID! The ID must be a 9-digit number.");
                            continue;
                        }

                        // Check if first name contains letters only
                        if (!firstName.matches("^[a-zA-Z]*$")) {
                            showErrorMessage("Invalid First Name! Only letters are allowed.");
                            continue;
                        }

                        // Check if last name contains letters only
                        if (!lastName.matches("^[a-zA-Z]*$")) {
                            showErrorMessage("Invalid Last Name! Only letters are allowed.");
                            continue;
                        }
                        // Check if the phone number is a valid israeli number
                        if (!phoneNumber.matches("^05\\d{8}$")) {
                            showErrorMessage("Invalid phone number! The phone number must start with '05' and have 10 digits.");
                            continue;
                        }

                        // Automatically capitalize the first letter of the first name and convert the rest to lowercase
                        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();

                        // Automatically capitalize the first letter of the last name and convert the rest to lowercase
                        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();

                        Area selectedArea = (Area) areaComboBox.getSelectedItem();
                        Gender selectedGender = (Gender) genderComboBox.getSelectedItem();
                        Integer selectedYearOfBirth = (Integer) birthYearComboBox.getSelectedItem();
                        boolean isVIP = vipCheckBox.isSelected();
                        double discountPercentage = 0.0;

                        if (isVIP) {
                            String discountText = discountField.getText();
                            if (discountText.isEmpty()) {
                                showErrorMessage("Please enter a discount percentage for VIP customers.");
                                continue;
                            } else {
                                try {
                                    discountPercentage = Double.parseDouble(discountText);
                                    if (discountPercentage < 0 || discountPercentage > 100) {
                                        showErrorMessage("Invalid discount percentage. Please enter a value between 0 and 100.");
                                        continue;
                                    }
                                } catch (NumberFormatException ex) {
                                    showErrorMessage("Invalid discount percentage. Please enter a valid number.");
                                    continue;
                                }
                            }
                        }

                        // Get selected date of joining
                        int selectedDay = (Integer) daysComboBox.getSelectedItem();
                        @SuppressWarnings("unused")
                        String selectedMonth = (String) monthsComboBox.getSelectedItem();
                        int selectedYear = (Integer) yearsComboBox.getSelectedItem();

                        Calendar calendar = Calendar.getInstance();
                        calendar.set(selectedYear, monthsComboBox.getSelectedIndex(), selectedDay);
                        Date dateOfJoining = calendar.getTime();

                        // Create and register the customer (including VIP if applicable)
                        Customer newCustomer;
                        if (isVIP) {
                            newCustomer = new VIPCustomer(id, firstName, lastName, phoneNumber, selectedArea, selectedGender, selectedYearOfBirth, dateOfJoining, discountPercentage);
                        } else {
                            newCustomer = new Customer(id, firstName, lastName, phoneNumber, selectedArea, selectedGender, selectedYearOfBirth, dateOfJoining);
                        }

                        try {
                            // Attempt to add the customer
                            boolean isAdded = Hotel.getInstance().addCustomer(newCustomer);
                            if (isAdded) {
                                // Show a success message
                                JOptionPane.showMessageDialog(frame, isVIP ? "VIP Customer added successfully!" : "Customer added successfully!");
                                break;
                            } else {
                                // Show a message indicating that the person already exists
                                JOptionPane.showMessageDialog(frame, isVIP ? "VIP Customer already exists!" : "Customer already exists!");
                            }
                        } catch (PersonAlreadyExistException ex) {
                            // Show a message indicating that a person with the same ID already exists
                            JOptionPane.showMessageDialog(frame, isVIP ? "VIP Customer with the same ID already exists!" : "Customer with the same ID already exists!");
                        }
                    } else {
                        // The user clicked cancel, exit the loop
                        break;
                    }
                } while (true);
            }
        });
        addFunctionsMenu.add(addCustomerMenuItem);


        // Add Employee/Department Manager
        JMenuItem addEmployeeMenuItem = new JMenuItem("Add Employee");
        addEmployeeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAdmin) {
                    // Employee should not have access to this method
                    JOptionPane.showMessageDialog(frame, "You do not have permission to access this feature.",
                            "Access Denied", JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (Hotel.getInstance().getAllDepartments().isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Can't add an employee without any departments!", "Access Denied",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Create a custom dialog for employee registration
                JPanel employeePanel = new JPanel(new GridLayout(0, 2, 5, 5));

                JTextField idField = new JTextField();
                JTextField firstNameField = new JTextField();
                JTextField lastNameField = new JTextField();
                JTextField phoneNumberField = new JTextField();
                JTextField salaryField = new JTextField();

                // Create the "Bonus" label
                JLabel bonusLabel = new JLabel("Bonus:");

                // Create the "Bonus" text field and set its preferred size
                JTextField bonusField = new JTextField();
                bonusField.setPreferredSize(new Dimension(100, bonusField.getPreferredSize().height));

                JComboBox<Department> departmentComboBox = new JComboBox<>(Hotel.getInstance().getAllDepartments().values().toArray(new Department[0]));
                departmentComboBox.setRenderer(new DepartmentRenderer());

                // Create and add other input fields for employee registration
                JComboBox<Area> areaComboBox = new JComboBox<>(Area.values());
                JComboBox<Gender> genderComboBox = new JComboBox<>(Gender.values());

                Integer[] birthYears = new Integer[66]; // Years from 1940 to 2005
                for (int i = 0; i < 66; i++) {
                    birthYears[i] = 1940 + i;
                }
                JComboBox<Integer> birthYearComboBox = new JComboBox<>(birthYears);

                Integer[] startOfWorkArray = new Integer[34];
                for (int i = 0; i < 34; i++) {
                    startOfWorkArray[i] = 1990 + i;
                }
                JComboBox<Integer> startOfWorkComboBox = new JComboBox<>(startOfWorkArray);

                // Add ActionListener to birthYearComboBox
                birthYearComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        int selectedBirthYear = (Integer) birthYearComboBox.getSelectedItem();
                        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                        Integer[] updatedStartOfWorkArray = new Integer[currentYear - selectedBirthYear - 16 + 1];
                        for (int i = 0; i <= currentYear - selectedBirthYear - 16; i++) {
                            updatedStartOfWorkArray[i] = selectedBirthYear + 16 + i;
                        }

                        // Update startOfWorkComboBox with the updatedStartOfWorkArray
                        DefaultComboBoxModel<Integer> startOfWorkModel = new DefaultComboBoxModel<>(updatedStartOfWorkArray);
                        startOfWorkComboBox.setModel(startOfWorkModel);
                    }
                });

                // Create the "Is Manager" checkbox
                JCheckBox managerCheckBox = new JCheckBox("Is Manager");

                // Define action for Manager checkbox
                managerCheckBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        boolean isManager = managerCheckBox.isSelected();
                        bonusLabel.setVisible(isManager);
                        bonusField.setVisible(isManager);
                    }
                });

                employeePanel.add(new JLabel("Employee ID:"));
                employeePanel.add(idField);
                employeePanel.add(new JLabel("First Name:"));
                employeePanel.add(firstNameField);
                employeePanel.add(new JLabel("Last Name:"));
                employeePanel.add(lastNameField);
                employeePanel.add(new JLabel("Phone Number:"));
                employeePanel.add(phoneNumberField);
                employeePanel.add(new JLabel("Area:"));
                employeePanel.add(areaComboBox);
                employeePanel.add(new JLabel("Gender:"));
                employeePanel.add(genderComboBox);
                employeePanel.add(new JLabel("Year of Birth:"));
                employeePanel.add(birthYearComboBox);
                employeePanel.add(new JLabel("Start of Work Year:"));
                employeePanel.add(startOfWorkComboBox);
                employeePanel.add(new JLabel("Salary:"));
                employeePanel.add(salaryField);
                employeePanel.add(new JLabel("Department:"));
                employeePanel.add(departmentComboBox);
                employeePanel.add(managerCheckBox);
                employeePanel.add(bonusLabel);
                bonusLabel.setVisible(false);
                employeePanel.add(bonusField);
                bonusField.setVisible(false);

                do {
                    int option = JOptionPane.showConfirmDialog(frame, employeePanel, "Add Employee", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (option == JOptionPane.OK_OPTION) {
                        // Rest of the code to process employee data
                        String id = idField.getText().trim();
                        String firstName = firstNameField.getText().trim();
                        String lastName = lastNameField.getText().trim();
                        String phoneNumber = phoneNumberField.getText().trim();
                        String salaryText = salaryField.getText().trim();
                        double salary = 0.0; // Default value for salary
                        Department department = (Department) departmentComboBox.getSelectedItem();
                        boolean isManager = managerCheckBox.isSelected();
                        double bonus = 0.0;

                        // Check if any required fields are empty
                        if (id.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || phoneNumber.isEmpty() || salaryText.isEmpty()) {
                            showErrorMessage("Please fill in all the required fields.");
                            continue;
                        }

                        // Parse the salary if not empty
                        try {
                            salary = Double.parseDouble(salaryText);
                        } catch (NumberFormatException ex) {
                            showErrorMessage("Invalid salary format. Please enter a valid number.");
                            continue;
                        }

                        // The first Employee added to an empty department should be the manager.
                        if (department.isEmpty() && !isManager) {
                            showErrorMessage("The selected department must have a manager as it is currently empty!");
                            continue;
                        }

                        // Admin can't add an employee of type department manager to a department that already has a manager
                        if (isManager && department.hasManager()) {
                            showErrorMessage("The selected department already has a manager!");
                            continue;
                        }

                        // Check if the ID is within the valid range
                        try {
                            Set<Integer> customerIds = new HashSet<>();
                            int employeeId = Integer.parseInt(id);
                            for (Customer customer : Hotel.getInstance().getAllCustomers().values()) {
                                customerIds.add(Integer.parseInt(customer.getId()));
                            }

                            if (employeeId < 100000000 || employeeId > 999999999) {
                                showErrorMessage("Invalid ID! The ID must be a 9-digit number.");
                                continue; // Do not proceed if the ID is not within the valid range
                            } else if (customerIds.contains(employeeId)) {
                                showErrorMessage("There is an employee with the same ID!");
                                continue; // Do not proceed if the ID is not within the valid range
                            }

                        } catch (NumberFormatException ex) {
                            showErrorMessage("Invalid ID! The ID must be a 9-digit number.");
                            continue; // Do not proceed if the ID is not a number or not 9 digits long
                        }

                        // Check if first name contains letters only
                        if (!firstName.matches("^[a-zA-Z]*$")) {
                            showErrorMessage("Invalid First Name! Only letters are allowed.");
                            continue; // Do not proceed if the first name contains non-letter characters
                        }

                        // Check if last name contains letters only
                        if (!lastName.matches("^[a-zA-Z]*$")) {
                            showErrorMessage("Invalid Last Name! Only letters are allowed.");
                            continue; // Do not proceed if the last name contains non-letter characters
                        }

                        if (!phoneNumber.matches("^05\\d{8}$")) {
                            showErrorMessage("Invalid phone number! The phone number must start with '05' and have 10 digits.");
                            continue; // Do not proceed if the phone number is invalid
                        }

                        // Automatically capitalize the first letter of the first name and convert the rest to lowercase
                        firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();

                        // Automatically capitalize the first letter of the last name and convert the rest to lowercase
                        lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();

                        Area selectedArea = (Area) areaComboBox.getSelectedItem();
                        Gender selectedGender = (Gender) genderComboBox.getSelectedItem();
                        int selectedYearOfBirth = (Integer) birthYearComboBox.getSelectedItem();
                        int startOfWork = (Integer) startOfWorkComboBox.getSelectedItem();

                        if (isManager) {
                            String bonusText = bonusField.getText().trim();
                            if (!bonusText.isEmpty()) {
                                try {
                                    bonus = Double.parseDouble(bonusText);
                                } catch (NumberFormatException ex) {
                                    showErrorMessage("Invalid bonus amount. Please enter a valid number.");
                                    continue; // Do not proceed if bonus amount is invalid
                                }
                            } else {
                                showErrorMessage("Please enter a bonus amount for managers.");
                                continue; // Do not proceed if bonus amount is empty for managers
                            }
                        }

                        // Create and register the employee (including manager if applicable)
                        Employee newEmployee;
                        if (isManager) {
                            newEmployee = new DepartmentManager(id, firstName, lastName, phoneNumber, selectedArea, selectedGender, selectedYearOfBirth, startOfWork, salary, department, bonus);
                        } else {
                            newEmployee = new Employee(id, firstName, lastName, phoneNumber, selectedArea, selectedGender, selectedYearOfBirth, startOfWork, salary, department);
                        }

                        try {
                            // Attempt to add the employee
                            boolean isAdded = false;
                            if (isManager) {
                                isAdded = Hotel.getInstance().addDepartmentManager((DepartmentManager) newEmployee);
                            } else {
                                isAdded = Hotel.getInstance().addEmployee(newEmployee);
                            }
                            if (isAdded) {
                                // Show a success message
                                JOptionPane.showMessageDialog(frame, isManager ? "Manager added successfully!" : "Employee added successfully!");
                                return;
                            } else {
                                // Show a message indicating that a person with the same ID already exists
                                showErrorMessage(isManager ? "Manager with the same ID already exists!" : "Employee with the same ID already exists!");
                            }
                        } catch (PersonAlreadyExistException ex) {
                            // Show a message indicating that a person with the same ID already exists
                            showErrorMessage(isManager ? "Manager with the same ID already exists!" : "Employee with the same ID already exists!");
                        }
                    } else {
                        // The user clicked cancel, exit the loop
                        break;
                    }
                } while (true);
            }
        });
        addFunctionsMenu.add(addEmployeeMenuItem);


        // Add Booking
        JMenuItem addBookingMenuItem = new JMenuItem("Add Booking");
        addBookingMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(Hotel.getInstance().getAllCustomers().isEmpty() && Hotel.getInstance().getAllRooms().isEmpty()){
                    JOptionPane.showMessageDialog(frame,"No Rooms and Customers Available!", "Access Denied",
                            JOptionPane.WARNING_MESSAGE);
                    return;
                } else if (Hotel.getInstance().getAllRooms().isEmpty()){
                JOptionPane.showMessageDialog(frame,"No Rooms Available!", "Access Denied",
                        JOptionPane.WARNING_MESSAGE);
                return;
            } else if(Hotel.getInstance().getAllCustomers().isEmpty()){
                JOptionPane.showMessageDialog(frame,"No Customers Available!", "Access Denied",
                        JOptionPane.WARNING_MESSAGE);
                return;
            }
                // Create a custom dialog for booking
                JPanel bookingPanel = new JPanel(new GridLayout(0, 2, 5, 5));

                // Create and add other input fields for booking
                JComboBox<String> roomNumberComboBox = new JComboBox<>(Hotel.getInstance().getAllRooms().keySet().toArray(new String[0]));

                // Create a combo box with a selection of customer IDs
                JComboBox<String> customerIdComboBox = new JComboBox<>();
                for (Customer customer : Hotel.getInstance().getAllCustomers().values()) {
                    customerIdComboBox.addItem(customer.getId());
                }

                Integer[] daysArray = new Integer[31];
                for (int i = 0; i < 31; i++) {
                    daysArray[i] = i + 1;
                }
                @SuppressWarnings("unused")
				JComboBox<Integer> daysComboBox = new JComboBox<>(daysArray);

                String[] monthsArray = new String[]{
                        "January", "February", "March", "April", "May", "June", "July",
                        "August", "September", "October", "November", "December"
                };
                @SuppressWarnings("unused")
				JComboBox<String> monthsComboBox = new JComboBox<>(monthsArray);

                Integer[] yearsArray = new Integer[34];
                for (int i = 0; i < 34; i++) {
                    yearsArray[i] = 1990 + i;
                }
                JComboBox<Integer> yearsComboBox = new JComboBox<>(yearsArray);

                JSpinner numberOfDaysSpinner = new JSpinner(new SpinnerNumberModel(1, 1, 30, 1));

                // Date of check-in components
                JComboBox<Integer> checkInDayComboBox = new JComboBox<>(daysArray);
                JComboBox<String> checkInMonthComboBox = new JComboBox<>(monthsArray);
                JComboBox<Integer> checkInYearComboBox = new JComboBox<>(yearsArray);

                // Listener for customer selection changes
                customerIdComboBox.addActionListener(new ActionListener() {
                	// I want that the registrations will make sense, so that the date of joining of the customer and the check in date of the booking should be reasonable.
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedCustomerId = (String) customerIdComboBox.getSelectedItem();
                        Customer selectedCustomer = Hotel.getInstance().getRealCustomer(selectedCustomerId);
                        if (selectedCustomer != null) {
                            int selectedBirthYear = selectedCustomer.getYearOfBirth();
                            int currentYear = Calendar.getInstance().get(Calendar.YEAR);

                            int minimumYear = selectedBirthYear + 16;
                            int maximumYear = Math.min(currentYear, 2023);

                            Integer[] updatedYearsArray = new Integer[maximumYear - minimumYear + 1];
                            for (int i = 0; i < updatedYearsArray.length; i++) {
                                updatedYearsArray[i] = minimumYear + i;
                            }

                            // Update yearsComboBox with the updatedYearsArray
                            DefaultComboBoxModel<Integer> yearsModel = new DefaultComboBoxModel<>(updatedYearsArray);
                            yearsComboBox.setModel(yearsModel);

                            // Set the check-in date combo boxes based on the customer's date of joining
                            checkInDayComboBox.setSelectedIndex(0);
                            checkInMonthComboBox.setSelectedIndex(0);
                            checkInYearComboBox.setSelectedItem(minimumYear);
                        }
                    }
                });

                // Adding the necessary fields.
                bookingPanel.add(new JLabel("Room Number:"));
                bookingPanel.add(roomNumberComboBox);
                bookingPanel.add(new JLabel("Choose a Customer's ID:"));
                bookingPanel.add(customerIdComboBox);
                bookingPanel.add(new JLabel("Number of Days:"));
                bookingPanel.add(numberOfDaysSpinner);

                bookingPanel.add(new JLabel("Check-In Date:"));
                JPanel checkInDatePanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 5, 0));
                checkInDatePanel.add(checkInDayComboBox);
                checkInDatePanel.add(checkInMonthComboBox);
                checkInDatePanel.add(checkInYearComboBox);
                bookingPanel.add(checkInDatePanel);

                int option = JOptionPane.showConfirmDialog(frame, bookingPanel, "Add Booking", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String roomNumber = (String) roomNumberComboBox.getSelectedItem();
                    String customerId = (String) customerIdComboBox.getSelectedItem();
                    Customer customer = Hotel.getInstance().getRealCustomer(customerId);
                    int numberOfDays = (int) numberOfDaysSpinner.getValue();

                    int checkInDay = (int) checkInDayComboBox.getSelectedItem();
                    @SuppressWarnings("unused")
					String checkInMonth = (String) checkInMonthComboBox.getSelectedItem();
                    int checkInYear = (int) checkInYearComboBox.getSelectedItem();

                    Calendar calendar = Calendar.getInstance();
                    calendar.set(checkInYear, checkInMonthComboBox.getSelectedIndex(), checkInDay);
                    Date checkInDate = calendar.getTime();

                    // Check if any required fields are empty
                    if (roomNumber.isEmpty() || customerId.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please fill in all the required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        return; // Do not proceed if any required fields are empty
                    }

                    // Validate customer's age (at least 16 years old)
                    int birthYear = customer.getYearOfBirth();
                    Calendar currentDate = Calendar.getInstance();
                    int currentYear = currentDate.get(Calendar.YEAR);

                    if (currentYear - birthYear < 16) {
                        // The customer is not at least 16 years old
                        JOptionPane.showMessageDialog(frame, "Customers must be at least 16 years old to book a reservation.", "Age Requirement", JOptionPane.ERROR_MESSAGE);
                        return; // Do not proceed with the booking
                    }

                    // Find the room by room number and get the daily price
                    Room room = null;
                    for (Map.Entry<String, Room> entry : Hotel.getInstance().getAllRooms().entrySet()) {
                        if (entry.getValue().getRoomNumber().equals(roomNumber)) {
                            room = entry.getValue();
                        }
                    }

                    // Calculate the total cost of the booking
                    double roomPricePerDay = room != null ? room.getDailyPrice() : 0.0;
                    double totalCost = roomPricePerDay * numberOfDays;

                    // If the customer is a VIP, apply the discount percentage
                    if (customer instanceof VIPCustomer) {
                        VIPCustomer vipCustomer = (VIPCustomer) customer;
                        double discountPercentage = vipCustomer.getDiscountPercentage();
                        double discountAmount = totalCost * (discountPercentage / 100);
                        totalCost -= discountAmount;
                    }

                    // Continue with the booking process
                    Booking newBooking = new Booking(roomNumber, customer, checkInDate, numberOfDays);
                    boolean isAdded = Hotel.getInstance().addBooking(newBooking);
                    if (isAdded) {
                        // Show a success message
                        JOptionPane.showMessageDialog(frame, "Booking added successfully!");
                    } else {
                        // Show a message indicating that the booking already exists
                        JOptionPane.showMessageDialog(frame, "Booking already exists!");
                    }
                }
            }
        });
        addFunctionsMenu.add(addBookingMenuItem);

        // Add Room
        JMenuItem addRoomMenuItem = new JMenuItem("Add Room");
        addRoomMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog for adding a room
                JPanel roomPanel = new JPanel(new GridLayout(0, 2, 5, 5));

                // Fields
                JTextField priceField = new JTextField();
                JComboBox<String> floorComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5", "6", "7"});
                JComboBox<String> roomTypeComboBox = new JComboBox<>(new String[]{"Standard", "Superior", "Suite"});
                JComboBox<String> roomGradeComboBox = new JComboBox<>(new String[]{"1", "2", "3", "4", "5"});
                JTextField avgDailyCostField = new JTextField();
                JTextField sizeField = new JTextField();
                JComboBox<Integer> numberOfGuestsComboBox = new JComboBox<>();
                JCheckBox hasViewCheckBox = new JCheckBox("Has View");
                JCheckBox hasJaccoziCheckBox = new JCheckBox("Has Jacuzzi");
                JTextField balconySizeField = new JTextField();

                // Hide the fields initially since StandardRoom is the default type
                hasViewCheckBox.setVisible(false);
                hasJaccoziCheckBox.setVisible(false);
                balconySizeField.setVisible(false);

                // Listener for room type selection changes
                roomTypeComboBox.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        String selectedRoomType = (String) roomTypeComboBox.getSelectedItem();
                        // Show/hide fields based on the selected room type
                        hasViewCheckBox.setVisible(selectedRoomType.equals("Standard") || selectedRoomType.equals("Superior") || selectedRoomType.equals("Suite"));
                        hasJaccoziCheckBox.setVisible(selectedRoomType.equals("Superior") || selectedRoomType.equals("Suite"));
                        balconySizeField.setVisible(selectedRoomType.equals("Suite"));

                        // Set the number of guests based on the selected room type
                        switch (selectedRoomType) {
                            case "Standard":
                                numberOfGuestsComboBox.removeAllItems();
                                numberOfGuestsComboBox.addItem(1);
                                numberOfGuestsComboBox.addItem(2);
                                break;
                            case "Superior":
                                numberOfGuestsComboBox.removeAllItems();
                                numberOfGuestsComboBox.addItem(3);
                                numberOfGuestsComboBox.addItem(4);
                                numberOfGuestsComboBox.addItem(5);
                                break;
                            case "Suite":
                                numberOfGuestsComboBox.removeAllItems();
                                numberOfGuestsComboBox.addItem(1);
                                numberOfGuestsComboBox.addItem(2);
                                numberOfGuestsComboBox.addItem(3);
                                numberOfGuestsComboBox.addItem(4);
                                numberOfGuestsComboBox.addItem(5);
                                numberOfGuestsComboBox.addItem(6);
                                numberOfGuestsComboBox.addItem(7);
                                break;
                        }
                    }
                });

                roomPanel.add(new JLabel("Price (100-1000):"));
                roomPanel.add(priceField);
                roomPanel.add(new JLabel("Floor:"));
                roomPanel.add(floorComboBox);
                roomPanel.add(new JLabel("Room Type:"));
                roomPanel.add(roomTypeComboBox);
                roomPanel.add(new JLabel("Average Daily Cost:"));
                roomPanel.add(avgDailyCostField);
                roomPanel.add(new JLabel("Room Grade:"));
                roomPanel.add(roomGradeComboBox);
                roomPanel.add(new JLabel("Size (30-50):"));
                roomPanel.add(sizeField);
                roomPanel.add(new JLabel("Number of Guests:"));
                roomPanel.add(numberOfGuestsComboBox);
                roomPanel.add(hasViewCheckBox);
                roomPanel.add(hasJaccoziCheckBox);
                roomPanel.add(new JLabel("Balcony Size:"));
                roomPanel.add(balconySizeField);

                do {
                    int option = JOptionPane.showConfirmDialog(frame, roomPanel, "Add Room", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                    if (option == JOptionPane.OK_OPTION) {
                        // Check if any required fields are empty
                        if (priceField.getText().trim().isEmpty()
                                || avgDailyCostField.getText().trim().isEmpty()
                                || sizeField.getText().trim().isEmpty()) {
                            showErrorMessage("Please fill in all the required fields.");
                            continue;
                        }

                        try {
                            double price = Double.parseDouble(priceField.getText().trim());
                            int floor = Integer.parseInt((String) floorComboBox.getSelectedItem());
                            double avgDailyCost = Double.parseDouble(avgDailyCostField.getText().trim());
                            int roomGrade = Integer.parseInt((String) roomGradeComboBox.getSelectedItem());
                            int size = Integer.parseInt(sizeField.getText().trim());
                            int numberOfGuests = (int) numberOfGuestsComboBox.getSelectedItem();
                            boolean hasView = hasViewCheckBox.isSelected();
                            boolean hasJaccozi = hasJaccoziCheckBox.isSelected();
                            double balconySize = 0.0;

                            // Check if price is within the valid range (100-1000)
                            if (price < 100 || price > 1000) {
                                showErrorMessage("Price must be between 100 and 1000.");
                                continue;
                            }

                            // Check if size is within the valid range (30-50)
                            if (size < 30 || size > 50) {
                                showErrorMessage("Size must be between 30 and 50.");
                                continue;
                            }

                            // It makes sense that the price should be larger than the cost
                            if (avgDailyCost > price) {
                                showErrorMessage("Cost must be less than price.");
                                continue;
                            }

                            //TO DO -
                            //DEFAULT NUMBER OF GUESTS TO ONE, DEFAULT TYPE OF ROOM TO STANDARD ROOM.

                            // If Suite is selected, check if the balcony size is provided and numeric
                            if (roomTypeComboBox.getSelectedItem().equals("Suite")) {
                                if (balconySizeField.isVisible()) {
                                    String balconySizeText = balconySizeField.getText().trim();
                                    if (!balconySizeText.isEmpty()) {
                                        balconySize = Double.parseDouble(balconySizeText);
                                    } else {
                                        showErrorMessage("Please enter a value for Balcony Size.");
                                        continue;
                                    }
                                }
                            }

                            // Add the room based on the selected room type
                            String roomType = (String) roomTypeComboBox.getSelectedItem();
                            switch (roomType) {
                                case "Standard":
                                    StandardRoom newStandardRoom = new StandardRoom(price, floor, avgDailyCost, roomGrade, numberOfGuests, size, hasView);
                                    newStandardRoom.setMaxPopulationCapacity(numberOfGuests);
                                    try {
                                        Hotel.getInstance().addStandardRoom(newStandardRoom);
                                    } catch (MaxPopulationCapacityException ex) {
                                        showErrorMessage("Max capacity for Standard Room exceeded.");
                                    }
                                    showSuccessMessage("Standard Room booked successfully!");
                                    return;
                                case "Superior":
                                    SuperiorRoom newSuperiorRoom = new SuperiorRoom(price, floor, avgDailyCost, roomGrade, numberOfGuests, size, hasView, hasJaccozi);
                                    newSuperiorRoom.setMaxPopulationCapacity(numberOfGuests);
                                    try {
                                        Hotel.getInstance().addSuperiorRoom(newSuperiorRoom);
                                    } catch (MaxPopulationCapacityException ex) {
                                        showErrorMessage("Max capacity for Superior Room exceeded.");
                                    }
                                    showSuccessMessage("Superior Room booked successfully!");
                                    return;
                                case "Suite":
                                    Suite newSuite = new Suite(price, floor, avgDailyCost, roomGrade, numberOfGuests, size, hasView, hasJaccozi, balconySize);
                                    newSuite.setMaxPopulationCapacity(numberOfGuests);
                                    try {
                                        Hotel.getInstance().addSuite(newSuite);
                                    } catch (MaxPopulationCapacityException ex) {
                                        showErrorMessage("Max capacity for Suite exceeded.");
                                    }
                                    showSuccessMessage("Suite booked successfully!");
                                    return;
                                default:
                                    showErrorMessage("Invalid room type selected.");
                                    break;
                            }
                        } catch (NumberFormatException ex) {
                            showErrorMessage("Invalid input format.");
                        }
                    } else {
                        // The user clicked cancel, exit the loop
                        break;
                    }
                } while (true);
            }
        });

        addFunctionsMenu.add(addRoomMenuItem);



        //last thing to undo

        //Add department
        JMenuItem addDepartmentMenuItem = new JMenuItem("Add Department");
        addDepartmentMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (!isAdmin) {
                    // Employee should not have access to this method
                    JOptionPane.showMessageDialog(frame, "You do not have permission to access this feature.",
                            "Access Denied", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Create a custom dialog for adding a department
                JPanel departmentPanel = new JPanel(new GridLayout(0, 2, 5, 5));

                // Create and add input fields for department
                JTextField departmentIdField = new JTextField();
                JComboBox<Specialization> specializationComboBox = new JComboBox<>(Specialization.values());

                departmentPanel.add(new JLabel("Department ID:"));
                departmentPanel.add(departmentIdField);
                departmentPanel.add(new JLabel("Specialization:"));
                departmentPanel.add(specializationComboBox);

                int option = JOptionPane.showConfirmDialog(frame, departmentPanel, "Add Department", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    // Check if any required fields are empty
                    if (departmentIdField.getText().trim().isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please fill in all the required fields.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        String departmentId = departmentIdField.getText().trim();
                        Specialization specialization = (Specialization) specializationComboBox.getSelectedItem();

                        // Check if the department ID is valid
                        try {
                            int departmentIdInt = Integer.parseInt(departmentId);
                            if (departmentIdInt < 1 || departmentIdInt > 100) {
                                JOptionPane.showMessageDialog(frame, "Department ID must be between 1 and 100!", "Input Error", JOptionPane.ERROR_MESSAGE);
                                return;
                            }

                            // Check if the department ID already exists
                            if (Hotel.getInstance().getRealDepartment(departmentId) != null) {
                                JOptionPane.showMessageDialog(frame, "Department ID already exists!", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                // Create the department
                                Department newDepartment = new Department(departmentId, specialization);

                                // Add the department to the hotel
                                if (Hotel.getInstance().addDepartment(newDepartment)) {
                                    // Show a success message
                                    JOptionPane.showMessageDialog(frame, "Department added successfully!");
                                } else {
                                    JOptionPane.showMessageDialog(frame, "Error adding department!", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            }
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(frame, "Invalid Department ID format! Please enter a number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        addFunctionsMenu.add(addDepartmentMenuItem);


        /*// Set department manager
        JMenuItem setDepartmentManagerMenuItem = new JMenuItem("Set Department Manager");
        addFunctionsMenu.add(setDepartmentManagerMenuItem);
        setDepartmentManagerMenuItem.addActionListener(new ActionListener() {
        	
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (!isAdmin) {
                    // Employee should not have access to this method
                    JOptionPane.showMessageDialog(frame, "You do not have permission to access this feature.",
                            "Access Denied", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Create a custom dialog for setting the department manager
                JPanel departmentManagerPanel = new JPanel(new GridLayout(0, 2, 5, 5));

                // Create and add input fields for setting department manager
                JComboBox<String> departmentComboBox = new JComboBox<>();
                JComboBox<String> departmentManagerComboBox = new JComboBox<>();

                // Add departments' IDs to the combo box
                for (Department department : Hotel.getInstance().getAllDepartments().values()) {
                    departmentComboBox.addItem(department.getDepartmentId());
                }

                // Add department managers' IDs to the combo box
                for (Employee departmentManager : Hotel.getInstance().getAllEmployees().values()) {
                    if (departmentManager instanceof DepartmentManager) {
                        departmentManagerComboBox.addItem(departmentManager.getId());
                    }
                }

                departmentManagerPanel.add(new JLabel("Select Department:"));
                departmentManagerPanel.add(departmentComboBox);
                departmentManagerPanel.add(new JLabel("Select Department Manager:"));
                departmentManagerPanel.add(departmentManagerComboBox);

                int option = JOptionPane.showConfirmDialog(frame, departmentManagerPanel, "Set Department Manager", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String departmentId = (String) departmentComboBox.getSelectedItem();
                    String departmentManagerId = (String) departmentManagerComboBox.getSelectedItem();
                    Department department = Hotel.getInstance().getRealDepartment(departmentId);
                    DepartmentManager departmentManager = Hotel.getInstance().getRealDepartmentManager(departmentManagerId);
                    boolean sameDepartment=false;
                    for(Map.Entry<String, Employee> entry : Hotel.getInstance().getAllEmployees().entrySet()) {
                    	if(entry.getValue() instanceof DepartmentManager && entry.getValue().getDepartment().getDepartmentId().equals(departmentId)) {
                    		sameDepartment=true;
                    	}
                    }
                    if (department != null && departmentManager != null && sameDepartment==true) {
                        department.setDepManager(departmentManager);
                        JOptionPane.showMessageDialog(frame, "Department Manager set successfully!");
                    }
                    else {
                        JOptionPane.showMessageDialog(frame, "Invalid department or department manager selected.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
        addFunctionsMenu.add(setDepartmentManagerMenuItem);*/

        /**********************************/
        // REMOVE OPTIONS

        // Remove Customer option
        JMenuItem removeCustomerMenuItem = new JMenuItem("Remove Customer");
        removeCustomerMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog for removing a customer
                JPanel removeCustomerPanel = new JPanel(new GridLayout(0, 2, 5, 5));

                // Create and add input fields for removing a customer
                JComboBox<String> customerIdComboBox = new JComboBox<>();
                for (Customer customer : Hotel.getInstance().getAllCustomers().values()) {
                    customerIdComboBox.addItem(customer.getId());
                }

                removeCustomerPanel.add(new JLabel("Select Customer ID:"));
                removeCustomerPanel.add(customerIdComboBox);

                int option = JOptionPane.showConfirmDialog(frame, removeCustomerPanel, "Remove Customer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String customerId = (String) customerIdComboBox.getSelectedItem();

                    // Check if any required fields are empty
                    if (customerId == null || customerId.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please select a customer ID to remove.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Attempt to remove the customer
                        boolean isRemoved = false;
                        List<Customer> customersToRemove = new ArrayList<>();

                        // Find the customer by ID and add it to the customersToRemove list
                        for (Customer customer : Hotel.getInstance().getAllCustomers().values()) {
                            if (customer.getId().equals(customerId)) {
                                customersToRemove.add(customer);
                            }
                        }

                        // Remove the customers from the customer list
                        for (Customer customerToRemove : customersToRemove) {
                            if (customerToRemove instanceof Customer) {
                                isRemoved = Hotel.getInstance().removeCustomer(customerToRemove);
                            } else if (customerToRemove instanceof VIPCustomer) {
                                isRemoved = Hotel.getInstance().removeVIPCustomer((VIPCustomer) customerToRemove);
                            }
                        }

                        if (isRemoved) {
                            // Show a success message
                            JOptionPane.showMessageDialog(frame, "Customer removed successfully!");
                        } else {
                            // Show a message indicating that the customer does not exist or cannot be removed
                            JOptionPane.showMessageDialog(frame, "Customer does not exist or cannot be removed.", "Removal Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        removeFunctionsMenu.add(removeCustomerMenuItem);


        // Remove Employee option
        JMenuItem removeEmployeeMenuItem = new JMenuItem("Remove Employee");
        removeEmployeeMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isAdmin) {
                    // Employee should not have access to this method
                    JOptionPane.showMessageDialog(frame, "You do not have permission to access this feature.",
                            "Access Denied", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Create a custom dialog for removing an employee
                JPanel removeEmployeePanel = new JPanel(new GridLayout(0, 2, 5, 5));

                // Create and add input fields for removing an employee
                JComboBox<String> employeeIdComboBox = new JComboBox<>();
                for (Employee employee : Hotel.getInstance().getAllEmployees().values()) {
                    employeeIdComboBox.addItem(employee.getId());
                }

                removeEmployeePanel.add(new JLabel("Select Employee ID:"));
                removeEmployeePanel.add(employeeIdComboBox);

                int option = JOptionPane.showConfirmDialog(frame, removeEmployeePanel, "Remove Employee", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String employeeId = (String) employeeIdComboBox.getSelectedItem();

                    // Check if any required fields are empty
                    if (employeeId == null || employeeId.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please select an employee ID to remove.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Attempt to remove the employee
                        boolean isRemoved = false;
                        List<Employee> employeesToRemove = new ArrayList<>();

                        // Find the employee by ID and add it to the employeesToRemove list
                        for (Employee employee : Hotel.getInstance().getAllEmployees().values()) {
                            if (employee.getId().equals(employeeId)) {
                                employeesToRemove.add(employee);
                            }
                        }

                        // Remove the employees from the employee list
                        for (Employee employeeToRemove : employeesToRemove) {
                            if (employeeToRemove instanceof Employee) {
                                isRemoved = Hotel.getInstance().removeEmployee(employeeToRemove);
                            } else if (employeeToRemove instanceof DepartmentManager) {
                                isRemoved = Hotel.getInstance().removeDepartmentManager((DepartmentManager) employeeToRemove);
                            }
                        }

                        if (isRemoved) {
                            // Show a success message
                            JOptionPane.showMessageDialog(frame, "Employee removed successfully!");
                        } else {
                            // Show a message indicating that the employee does not exist or cannot be removed
                            JOptionPane.showMessageDialog(frame, "Employee does not exist or cannot be removed.", "Removal Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        removeFunctionsMenu.add(removeEmployeeMenuItem);



        // Remove Booking option
        JMenuItem removeBookingMenuItem = new JMenuItem("Remove Booking");
        removeBookingMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog for removing a department
                JPanel removeBookingPanel = new JPanel(new GridLayout(0, 2, 5, 5));

                // Create and add input fields for removing a department
                JComboBox<String> bookingNumberComboBox = new JComboBox<>();
                for (Booking booking : Hotel.getInstance().getAllBookings().values()) {
                    bookingNumberComboBox.addItem(booking.getBookingNumber());
                }

                removeBookingPanel.add(new JLabel("Select Booking Number:"));
                removeBookingPanel.add(bookingNumberComboBox);

                int option = JOptionPane.showConfirmDialog(frame, removeBookingPanel, "Remove Booking", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String bookingNumber = (String) bookingNumberComboBox.getSelectedItem();

                    // Check if any required fields are empty
                    if (bookingNumber == null || bookingNumber.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please select a Booking Number to remove.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Attempt to remove the department
                        Booking bookingToRemove=null;
                        for(Booking booking : Hotel.getInstance().getAllBookings().values()) {
                            if(bookingNumber==booking.getBookingNumber()) {
                                bookingToRemove=booking;
                            }
                        }
                        @SuppressWarnings("unused")
                        boolean isRemoved = Hotel.getInstance().removeBooking(bookingToRemove);
                        if (bookingToRemove==null) {
                            // Show a success message
                            JOptionPane.showMessageDialog(frame, "Booking does not exist or cannot be removed.", "Removal Error", JOptionPane.ERROR_MESSAGE);
                        } else {
                            Hotel.getInstance().removeBooking(bookingToRemove);
                            JOptionPane.showMessageDialog(frame, "Booking removed successfully!");
                        }
                    }
                }
            }
        });
        removeFunctionsMenu.add(removeBookingMenuItem);

        // Remove Room option
        JMenuItem removeRoomMenuItem = new JMenuItem("Remove Room");
        removeRoomMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog for removing a room
                JPanel removeRoomPanel = new JPanel(new GridLayout(0, 2, 5, 5));

                // Create and add input fields for removing a room
                JComboBox<String> roomNumberComboBox = new JComboBox<>();
                for (String roomNumber : Hotel.getInstance().getAllRooms().keySet()) {
                    roomNumberComboBox.addItem(roomNumber);
                }

                removeRoomPanel.add(new JLabel("Select Room Number:"));
                removeRoomPanel.add(roomNumberComboBox);

                int option = JOptionPane.showConfirmDialog(frame, removeRoomPanel, "Remove Room", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String roomNumber = (String) roomNumberComboBox.getSelectedItem();

                    // Check if any required fields are empty
                    if (roomNumber == null || roomNumber.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please select a room number to remove.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Attempt to remove the room
                        Room room = Hotel.getInstance().getAllRooms().get(roomNumber);
                        if (room != null) {
                            boolean isRemoved = false;
                            if (room instanceof StandardRoom) {
                                isRemoved = Hotel.getInstance().removeStandardRoom((StandardRoom) room);
                            } else if (room instanceof SuperiorRoom) {
                                isRemoved = Hotel.getInstance().removeSuperiorRoom((SuperiorRoom) room);
                            } else if (room instanceof Suite) {
                                isRemoved = Hotel.getInstance().removeSuite((Suite) room);
                            }

                            if (isRemoved) {
                                // Show a success message
                                JOptionPane.showMessageDialog(frame, "Room removed successfully!");
                            } else {
                                // Show a message indicating that the room does not exist
                                JOptionPane.showMessageDialog(frame, "Room does not exist or cannot be removed.", "Removal Error", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            // Show a message indicating that the room does not exist
                            JOptionPane.showMessageDialog(frame, "Room does not exist or cannot be removed.", "Removal Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });
        removeFunctionsMenu.add(removeRoomMenuItem);



     // Remove Department option
        JMenuItem removeDepartmentMenuItem = new JMenuItem("Remove Department");
        removeDepartmentMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	if (!isAdmin) {
                    // Employee should not have access to this method
                    JOptionPane.showMessageDialog(frame, "You do not have permission to access this feature.",
                            "Access Denied", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Create a custom dialog for removing a department
                JPanel removeDepartmentPanel = new JPanel(new GridLayout(0, 2, 5, 5));

                // Create and add input fields for removing a department
                JComboBox<String> departmentIdComboBox = new JComboBox<>();
                for (Department department : Hotel.getInstance().getAllDepartments().values()) {
                    departmentIdComboBox.addItem(department.getDepartmentId());
                }

                removeDepartmentPanel.add(new JLabel("Select Department ID:"));
                removeDepartmentPanel.add(departmentIdComboBox);

                int option = JOptionPane.showConfirmDialog(frame, removeDepartmentPanel, "Remove Department", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String departmentId = (String) departmentIdComboBox.getSelectedItem();

                    // Check if any required fields are empty
                    if (departmentId == null || departmentId.isEmpty()) {
                        JOptionPane.showMessageDialog(frame, "Please select a department ID to remove.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        // Attempt to remove the department
                    	Department toRemoveDaDepartment=null;
                    	for(Department department : Hotel.getInstance().getAllDepartments().values()) {
                    		if(departmentId==department.getDepartmentId()) {
                    			toRemoveDaDepartment=department;
                    		}
                    	}
                        boolean isRemoved = Hotel.getInstance().removeDepartment(toRemoveDaDepartment);
                        if (isRemoved) {
                            // Show a success message
                            JOptionPane.showMessageDialog(frame, "Department removed successfully!");
                        } else {
                            // Show a message indicating that the department does not exist
                            JOptionPane.showMessageDialog(frame, "Department does not exist or cannot be removed.", "Removal Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        removeFunctionsMenu.add(removeDepartmentMenuItem);

        /**********************************/
        // GET REAL OPTIONS


        //Get Real Customer
        JMenuItem getRealCustomer = new JMenuItem("Get Customer");
        getRealCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog for removing a department
                JPanel getRealCustomerPanel = new JPanel(new GridLayout(0, 2, 5, 5));

                // Create and add input fields for removing a department
                JComboBox<String> customerIdBox = new JComboBox<>();
                for (Customer customer : Hotel.getInstance().getAllCustomers().values()) {
                    customerIdBox.addItem(customer.getId());
                }

                getRealCustomerPanel.add(new JLabel("Select Customer ID:"));
                getRealCustomerPanel.add(customerIdBox);

                int option = JOptionPane.showConfirmDialog(frame, getRealCustomerPanel, "Get Customer", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String customerId = (String) customerIdBox.getSelectedItem();
                    Customer customerToShow=null;
                    for (Customer customer : Hotel.getInstance().getAllCustomers().values()) {
                        if(customer.getId()==customerId && customer instanceof VIPCustomer) {
                            customerToShow=(VIPCustomer)customer;
                            JOptionPane.showMessageDialog(frame, customerToShow.toString());
                        }
                        else if(customer.getId()==customerId) {
                            customerToShow=customer;
                            JOptionPane.showMessageDialog(frame, customerToShow.toString());
                        }
                    }
                    if(customerToShow==null) {
                        JOptionPane.showMessageDialog(frame, "Customer is not available!");
                    }

                }
            }

        });
        getRealFunctionsMenu.add(getRealCustomer);


        //Get Real Employee
        JMenuItem getRealEmployee = new JMenuItem("Get Employee");
        getRealEmployee.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog for removing a department
                JPanel getRealEmployeePanel = new JPanel(new GridLayout(0, 2, 5, 5));

                // Create and add input fields for removing a department
                JComboBox<String> employeeIdBox = new JComboBox<>();
                for (Employee employee : Hotel.getInstance().getAllEmployees().values()) {
                    employeeIdBox.addItem(employee.getId());
                }

                getRealEmployeePanel.add(new JLabel("Select Employee ID:"));
                getRealEmployeePanel.add(employeeIdBox);

                int option = JOptionPane.showConfirmDialog(frame, getRealEmployeePanel, "Get Employee", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String employeeId = (String) employeeIdBox.getSelectedItem();
                    Employee employeeToShow=null;
                    for (Employee employee : Hotel.getInstance().getAllEmployees().values()) {
                        if(employee.getId()==employeeId && employee instanceof DepartmentManager) {
                            employeeToShow=(DepartmentManager)employee;
                            JOptionPane.showMessageDialog(frame, employeeToShow.toString());
                        }
                        else if(employee.getId()==employeeId) {
                            employeeToShow=employee;
                            JOptionPane.showMessageDialog(frame, employeeToShow.toString());
                        }
                    }
                    if(employeeToShow==null) {
                        JOptionPane.showMessageDialog(frame, "Employee is not available!");
                    }

                }
            }

        });
        getRealFunctionsMenu.add(getRealEmployee);

        
        //Get Room
        JMenuItem getRealRoom = new JMenuItem("Get Real Room");
        getRealRoom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog for removing a department
                JPanel getRealRoomPanel = new JPanel(new GridLayout(0, 2, 5, 5));

                // Create and add input fields for removing a department
                JComboBox<String> roomIdBox = new JComboBox<>();
                for (Room room : Hotel.getInstance().getAllRooms().values()) {
                	roomIdBox.addItem(room.getRoomNumber());
                }

                getRealRoomPanel.add(new JLabel("Select Room Number:"));
                getRealRoomPanel.add(roomIdBox);

                int option = JOptionPane.showConfirmDialog(frame, getRealRoomPanel, "Get Room", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String roomNumber = (String) roomIdBox.getSelectedItem();
                    Room roomToShow=null;
                    boolean shown=false;
                    for(Room room : Hotel.getInstance().getAllRooms().values()) {
                    	if(roomNumber==room.getRoomNumber()) {
                            if(room instanceof StandardRoom) {
                            	roomToShow=(StandardRoom)room;
                            	JOptionPane.showMessageDialog(frame, roomToShow.toString());
                            	shown=true;
                            }
                            else if(room instanceof SuperiorRoom) {
                            	roomToShow=(SuperiorRoom)room;
                            	JOptionPane.showMessageDialog(frame, roomToShow.toString());
                            	shown=true;
                            }
                            else {
                            	roomToShow=(Suite)room;
                            	JOptionPane.showMessageDialog(frame, roomToShow.toString());
                            	shown=true;
                            }
                    	}
                    }
                    if(shown==false) {
                    	JOptionPane.showMessageDialog(frame, "Room is not available!");
                    }
                        
                   }
               }
            
        });

        getRealFunctionsMenu.add(getRealRoom);
        
        //Get Real Booking
        JMenuItem getRealBooking = new JMenuItem("Get Booking");
        getRealBooking.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog for removing a department
                JPanel getRealBookingPanel = new JPanel(new GridLayout(0, 2, 5, 5));

                // Create and add input fields for removing a department
                JComboBox<String> bookingNumberBox = new JComboBox<>();
                for (Booking b : Hotel.getInstance().getAllBookings().values()) {
                	bookingNumberBox.addItem(b.getBookingNumber());
                }

                getRealBookingPanel.add(new JLabel("Select Booking Number:"));
                getRealBookingPanel.add(bookingNumberBox);

                int option = JOptionPane.showConfirmDialog(frame, getRealBookingPanel, "Get Booking", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String bookingNumber = (String) bookingNumberBox.getSelectedItem();
                    boolean shown=false;
                    for(Booking b : Hotel.getInstance().getAllBookings().values()) {
                    	if(b.getBookingNumber().equals(bookingNumber)) {
                    		JOptionPane.showMessageDialog(frame, b.toString());
                    		shown=true;
                    	}
                    }
                    if(shown==false) {
                    	JOptionPane.showMessageDialog(frame, "Booking is not available!");
                    }
                        
                   }
               }
            
        });
        getRealFunctionsMenu.add(getRealBooking);



        
        //Get Real Department
        JMenuItem getRealDepartment = new JMenuItem("Get Department");
        getRealDepartment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Create a custom dialog for removing a department
                JPanel getRealDepartmentPanel = new JPanel(new GridLayout(0, 2, 5, 5));

                // Create and add input fields for removing a department
                JComboBox<String> departmentIdBox = new JComboBox<>();
                for (Department department : Hotel.getInstance().getAllDepartments().values()) {
                	departmentIdBox.addItem(department.getDepartmentId());
                }

                getRealDepartmentPanel.add(new JLabel("Select Department ID:"));
                getRealDepartmentPanel.add(departmentIdBox);

                int option = JOptionPane.showConfirmDialog(frame, getRealDepartmentPanel, "Get Department", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    String departmentId = (String) departmentIdBox.getSelectedItem();
                    Department departmentToShow=null;
                    for (Department department : Hotel.getInstance().getAllDepartments().values()) {
                    	if(departmentId==department.getDepartmentId()) {
                    		departmentToShow=department;
                    		JOptionPane.showMessageDialog(frame, departmentToShow.toString());
                    	}
                    }
                    if(departmentToShow==null) {
                    	JOptionPane.showMessageDialog(frame, "Department is not available!");
                    }
                        
                   }
               }
            
        });
        getRealFunctionsMenu.add(getRealDepartment);

        /**********************************/
        // QUERIES OPTIONS
      
        // kemployees option
        JMenuItem kemployeesMenuItem = new JMenuItem("Sorted K Employees");
        kemployeesMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel kEmployeesPanel = new JPanel(new GridLayout(0, 1, 5, 5));
                JComboBox<Integer> numberOfEmployeesComboBox = new JComboBox<>();

                // Populate the number of employees combo box with values
                for (int i = 1; i <= Hotel.getInstance().getAllEmployees().size(); i++) {
                    numberOfEmployeesComboBox.addItem(i);
                }

                kEmployeesPanel.add(new JLabel("Select the number of employees (k):"));
                kEmployeesPanel.add(numberOfEmployeesComboBox);

                int option = JOptionPane.showConfirmDialog(frame, kEmployeesPanel, "kemployees", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    int k = (int) numberOfEmployeesComboBox.getSelectedItem();

                    // Perform the "kemployees" query
                    List<Employee> topKEmployees = Hotel.getInstance().KEmployees(k);

                    // Display the results of the query
                    StringBuilder resultMessage = new StringBuilder("Top " + k + " Employees (sorted by ID):\n");
                    for (Employee employee : topKEmployees) {
                        resultMessage.append(employee.getId()).append(": ").append(employee.getName()).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, resultMessage.toString());
                }
            }
        });
        querieMenu.add(kemployeesMenuItem);

        //All Customers BY ID
        JMenuItem allCustomersByPK = new JMenuItem("All Customers By ID");
        allCustomersByPK.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the allCustomersByPK method from the Hotel class to get the sorted list
                ArrayList<Customer> allCustomers = Hotel.getInstance().allCustomersByPK();
                Collections.sort(allCustomers);

                if (allCustomers.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No registered customers at the moment.", "Empty Customer List", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Create a new JFrame to display the sorted list of customers
                    JFrame customerListFrame = new JFrame("Sorted Customer List");
                    customerListFrame.setLayout(new BorderLayout());

                    // Create a JTextArea to display the customer details
                    JTextArea customerTextArea = new JTextArea(20, 40);
                    customerTextArea.setEditable(false);

                    // Append customer details to the JTextArea
                    StringBuilder customerList = new StringBuilder();
                    for (Customer customer : allCustomers) {
                        customerList.append("Customer ID: ").append(customer.getId()).append("\n");
                        customerList.append("Name: ").append(customer.getFirstName()).append(" ").append(customer.getLastName()).append("\n");
                        customerList.append("Phone Number: ").append(customer.getPhoneNumber()).append("\n");
                        customerList.append("Area: ").append(customer.getArea()).append("\n");
                        customerList.append("Gender: ").append(customer.getGender()).append("\n");
                        customerList.append("Year of Birth: ").append(customer.getYearOfBirth()).append("\n");
                        customerList.append("Date of Joining: ").append(customer.getDateOfJoining()).append("\n");

                        if (customer instanceof VIPCustomer) {
                            VIPCustomer vipCustomer = (VIPCustomer) customer;
                            customerList.append("VIP Customer: Yes\n");
                            customerList.append("Discount Percentage: ").append(vipCustomer.getDiscountPercentage()).append("\n");
                        } else {
                            customerList.append("VIP Customer: No\n");
                        }
                        customerList.append("\n");
                    }

                    customerTextArea.setText(customerList.toString());

                    // Add the JTextArea to a JScrollPane
                    JScrollPane scrollPane = new JScrollPane(customerTextArea);

                    // Add the JScrollPane to the customerListFrame
                    customerListFrame.add(scrollPane, BorderLayout.CENTER);

                    // Create the "Sort Now" and "Cancel" buttons
                    JPanel buttonPanel = new JPanel();
                    JButton sortButton = new JButton("Sort Now");
                    JButton cancelButton = new JButton("Cancel");

                    // Add action listener for the "Sort Now" button
                    sortButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            // Implement sorting logic here if needed
                            // For this example, the customers are already sorted, so no action is required.
                            // You can add sorting logic if needed based on your requirements.
                        }
                    });

                    // Add action listener for the "Cancel" button
                    cancelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            customerListFrame.dispose(); // Close the frame when "Cancel" is clicked.
                        }
                    });

                    // Add the buttons to the button panel
                    buttonPanel.add(sortButton);
                    buttonPanel.add(cancelButton);

                    // Add the button panel to the customerListFrame
                    customerListFrame.add(buttonPanel, BorderLayout.SOUTH);

                    // Set the frame size and visibility
                    customerListFrame.pack();
                    customerListFrame.setLocationRelativeTo(null);
                    customerListFrame.setVisible(true);
                }
            }
        });
        querieMenu.add(allCustomersByPK);


        //All Bookings By Revenue
        JMenuItem allBookingsByRevenue = new JMenuItem("All Bookings By Revenue");
        allBookingsByRevenue.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the allBookingByRevenue method from the Hotel class to get the sorted list
                TreeSet<Booking> allBookings = Hotel.getInstance().allBookingByRevenue();

                if (allBookings.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No bookings found at the moment.", "Empty Booking List", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Create a new JFrame to display the sorted list of bookings
                    JFrame bookingListFrame = new JFrame("Sorted Booking List");
                    bookingListFrame.setLayout(new BorderLayout());

                    // Create a JTextArea to display the booking details
                    JTextArea bookingTextArea = new JTextArea(20, 40);
                    bookingTextArea.setEditable(false);

                    // Append booking details to the JTextArea
                    StringBuilder bookingList = new StringBuilder();
                    for (Booking booking : allBookings) {
                        bookingList.append("Booking Number: ").append(booking.getBookingNumber()).append("\n");
                        bookingList.append("Customer ID: ").append(booking.getCustomer().getId()).append("\n");
                        bookingList.append("Room Number: ").append(booking.getRoomNumber()).append("\n");
                        bookingList.append("Check-in Date: ").append(booking.getCheckInDate()).append("\n");
                        bookingList.append("Total Revenue: ").append(booking.getTotalRevenue()).append("\n");
                        bookingList.append("\n");
                    }

                    bookingTextArea.setText(bookingList.toString());

                    // Add the JTextArea to a JScrollPane
                    JScrollPane scrollPane = new JScrollPane(bookingTextArea);

                    // Add the JScrollPane to the bookingListFrame
                    bookingListFrame.add(scrollPane, BorderLayout.CENTER);

                    // Create the "Cancel" button
                    JPanel buttonPanel = new JPanel();
                    JButton cancelButton = new JButton("Cancel");

                    // Add action listener for the "Cancel" button
                    cancelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            bookingListFrame.dispose(); // Close the frame when "Cancel" is clicked.
                        }
                    });

                    // Add the button to the button panel
                    buttonPanel.add(cancelButton);

                    // Add the button panel to the bookingListFrame
                    bookingListFrame.add(buttonPanel, BorderLayout.SOUTH);

                    // Set the frame size and visibility
                    bookingListFrame.pack();
                    bookingListFrame.setLocationRelativeTo(null);
                    bookingListFrame.setVisible(true);
                }
            }
        });
        querieMenu.add(allBookingsByRevenue);


        //All Customers By Bookings
        JMenuItem allCustomersByBookings = new JMenuItem("All Customers By Bookings");
        allCustomersByBookings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the allCustomersCmp method from the Hotel class to get the sorted list
                TreeSet<Customer> allCustomers = Hotel.getInstance().allCustomersCmp();

                if (allCustomers.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "No registered customers at the moment.", "Empty Customer List", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Create a new JFrame to display the sorted list of customers
                    JFrame customerListFrame = new JFrame("Sorted Customer List");
                    customerListFrame.setLayout(new BorderLayout());

                    // Create a JTextArea to display the customer details
                    JTextArea customerTextArea = new JTextArea(20, 40);
                    customerTextArea.setEditable(false);

                    // Append customer details to the JTextArea
                    StringBuilder customerList = new StringBuilder();
                    for (Customer customer : allCustomers) {
                        customerList.append("Customer ID: ").append(customer.getId()).append("\n");
                        customerList.append("Name: ").append(customer.getFirstName()).append(" ").append(customer.getLastName()).append("\n");
                        customerList.append("Phone Number: ").append(customer.getPhoneNumber()).append("\n");
                        customerList.append("Area: ").append(customer.getArea()).append("\n");
                        customerList.append("Gender: ").append(customer.getGender()).append("\n");
                        customerList.append("Year of Birth: ").append(customer.getYearOfBirth()).append("\n");
                        customerList.append("Date of Joining: ").append(customer.getDateOfJoining()).append("\n");
                        customerList.append("Number of Bookings: ").append(customer.getAllBookings().size()).append("\n");

                        if (customer instanceof VIPCustomer) {
                            VIPCustomer vipCustomer = (VIPCustomer) customer;
                            customerList.append("VIP Customer: Yes\n");
                            customerList.append("Discount Percentage: ").append(vipCustomer.getDiscountPercentage()).append("\n");
                        } else {
                            customerList.append("VIP Customer: No\n");
                        }
                        customerList.append("\n");
                    }

                    customerTextArea.setText(customerList.toString());

                    // Add the JTextArea to a JScrollPane
                    JScrollPane scrollPane = new JScrollPane(customerTextArea);

                    // Add the JScrollPane to the customerListFrame
                    customerListFrame.add(scrollPane, BorderLayout.CENTER);

                    // Create the "Cancel" button
                    JPanel buttonPanel = new JPanel();
                    JButton cancelButton = new JButton("Cancel");

                    // Add action listener for the "Cancel" button
                    cancelButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            customerListFrame.dispose(); // Close the frame when "Cancel" is clicked.
                        }
                    });

                    // Add the button to the button panel
                    buttonPanel.add(cancelButton);

                    // Add the button panel to the customerListFrame
                    customerListFrame.add(buttonPanel, BorderLayout.SOUTH);

                    // Set the frame size and visibility
                    customerListFrame.pack();
                    customerListFrame.setLocationRelativeTo(null);
                    customerListFrame.setVisible(true);
                }
            }
        });
        querieMenu.add(allCustomersByBookings);


        //Total Profit
        JMenuItem totalProfitMenuItem = new JMenuItem("Total Profit");
        totalProfitMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the totalProfit method from the Hotel class to calculate the total profit
                int totalProfit = Hotel.getInstance().totalProfit();

                // Create a new JFrame to display the total profit
                JFrame totalProfitFrame = new JFrame("Total Profit");
                totalProfitFrame.setLayout(new BorderLayout());

                // Create a JTextArea to display the total profit
                JTextArea totalProfitTextArea = new JTextArea(5, 20);
                totalProfitTextArea.setEditable(false);

                // Set the text of the JTextArea to display the total profit
                totalProfitTextArea.setText("Total Profit: $" + totalProfit + "\n");

                // Add the JTextArea to a JScrollPane
                JScrollPane scrollPane = new JScrollPane(totalProfitTextArea);

                // Add the JScrollPane to the totalProfitFrame
                totalProfitFrame.add(scrollPane, BorderLayout.CENTER);

                // Create the "Close" button
                JPanel buttonPanel = new JPanel();
                JButton closeButton = new JButton("Close");

                // Add action listener for the "Close" button
                closeButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        totalProfitFrame.dispose(); // Close the frame when "Close" is clicked.
                    }
                });

                // Add the button to the button panel
                buttonPanel.add(closeButton);

                // Add the button panel to the totalProfitFrame
                totalProfitFrame.add(buttonPanel, BorderLayout.SOUTH);

                // Set the frame size and visibility
                totalProfitFrame.pack();
                totalProfitFrame.setLocationRelativeTo(null);
                totalProfitFrame.setVisible(true);
            }
        });
        querieMenu.add(totalProfitMenuItem);


        //All Bookings of a Specific Customer
        JMenuItem allBookingsOfSpecCustomerMenuItem = new JMenuItem("All Bookings of a Specific Customer");
        allBookingsOfSpecCustomerMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get all customer IDs
                Set<String> customerIDs = Hotel.getInstance().getAllCustomers().keySet();
                String[] customerIDsArray = customerIDs.toArray(new String[0]);

                // Create a combo box with all customer IDs
                JComboBox<String> customerComboBox = new JComboBox<>(customerIDsArray);

                // Create a JPanel to hold the combo box
                JPanel comboPanel = new JPanel();
                comboPanel.add(new JLabel("Select Customer ID:"));
                comboPanel.add(customerComboBox);

                int option = JOptionPane.showConfirmDialog(frame, comboPanel, "Select Customer ID", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
                if (option == JOptionPane.OK_OPTION) {
                    // Get the selected customer ID
                    String selectedCustomerID = (String) customerComboBox.getSelectedItem();

                    // Get all bookings of the selected customer
                    List<Booking> customerBookings = Hotel.getInstance().allBookingsOfSpecCustomer(selectedCustomerID);

                    // Create a new JFrame to display the customer bookings
                    JFrame customerBookingsFrame = new JFrame("Customer Bookings");
                    customerBookingsFrame.setLayout(new BorderLayout());

                    // Create a JTextArea to display the customer bookings
                    JTextArea customerBookingsTextArea = new JTextArea(20, 40);
                    customerBookingsTextArea.setEditable(false);

                    // Append booking details to the JTextArea
                    StringBuilder bookingsList = new StringBuilder();
                    for (Booking booking : customerBookings) {
                        bookingsList.append("Booking Number: ").append(booking.getBookingNumber()).append("\n");
                        bookingsList.append("Customer ID: ").append(booking.getCustomer().getId()).append("\n");
                        bookingsList.append("Check-in Date: ").append(booking.getCheckInDate()).append("\n");
                        bookingsList.append("Room Number: ").append(booking.getRoomNumber()).append("\n");
                        bookingsList.append("Total Cost: ").append(booking.getTotalCost()).append("\n");
                        bookingsList.append("Total Revenue: ").append(booking.getTotalRevenue()).append("\n");
                        bookingsList.append("\n");
                    }

                    customerBookingsTextArea.setText(bookingsList.toString());

                    // Add the JTextArea to a JScrollPane
                    JScrollPane scrollPane = new JScrollPane(customerBookingsTextArea);

                    // Add the JScrollPane to the customerBookingsFrame
                    customerBookingsFrame.add(scrollPane, BorderLayout.CENTER);

                    // Create the "Close" button
                    JPanel buttonPanel = new JPanel();
                    JButton closeButton = new JButton("Close");

                    // Add action listener for the "Close" button
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            customerBookingsFrame.dispose(); // Close the frame when "Close" is clicked.
                        }
                    });

                    // Add the button to the button panel
                    buttonPanel.add(closeButton);

                    // Add the button panel to the customerBookingsFrame
                    customerBookingsFrame.add(buttonPanel, BorderLayout.SOUTH);

                    // Set the frame size and visibility
                    customerBookingsFrame.pack();
                    customerBookingsFrame.setLocationRelativeTo(null);
                    customerBookingsFrame.setVisible(true);
                }
            }
        });
        querieMenu.add(allBookingsOfSpecCustomerMenuItem);


        //Customer Who Booked the Most Rooms
        JMenuItem customerBookedMostRoomsMenuItem = new JMenuItem("Customer Who Booked the Most Rooms");
        customerBookedMostRoomsMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Call the customerBookedTheMostRooms method from the Hotel class to get the customer
                Customer customerWithMostRooms = Hotel.getInstance().customerBookedTheMostRooms();

                if (customerWithMostRooms == null) {
                    JOptionPane.showMessageDialog(frame, "No customers have booked any rooms.", "No Bookings", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Create a new JFrame to display the customer's details
                    JFrame customerDetailsFrame = new JFrame("Customer with Most Booked Rooms");
                    customerDetailsFrame.setLayout(new BorderLayout());

                    // Create a JTextArea to display the customer's details
                    JTextArea customerDetailsTextArea = new JTextArea(10, 30);
                    customerDetailsTextArea.setEditable(false);

                    // Display the customer's details using the toString method
                    customerDetailsTextArea.setText(customerWithMostRooms.toString()+"Number of bookings: "+customerWithMostRooms
                            .getAllBookings().size());

                    // Add the JTextArea to a JScrollPane
                    JScrollPane scrollPane = new JScrollPane(customerDetailsTextArea);

                    // Add the JScrollPane to the customerDetailsFrame
                    customerDetailsFrame.add(scrollPane, BorderLayout.CENTER);

                    // Create the "Close" button
                    JPanel buttonPanel = new JPanel();
                    JButton closeButton = new JButton("Close");

                    // Add action listener for the "Close" button
                    closeButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            customerDetailsFrame.dispose(); // Close the frame when "Close" is clicked.
                        }
                    });

                    // Add the button to the button panel
                    buttonPanel.add(closeButton);

                    // Add the button panel to the customerDetailsFrame
                    customerDetailsFrame.add(buttonPanel, BorderLayout.SOUTH);

                    // Set the frame size and visibility
                    customerDetailsFrame.pack();
                    customerDetailsFrame.setLocationRelativeTo(null);
                    customerDetailsFrame.setVisible(true);
                }
            }
        });
        querieMenu.add(customerBookedMostRoomsMenuItem);
        
        loginScreen.getLoginButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the entered username and password from the login screen
                String username = loginScreen.getUsernameField().getText();
                String password = new String(loginScreen.getPasswordField().getPassword());

                boolean isAdmin = loginScreen.isAdminLogin(username, password);
                boolean isEmployee = loginScreen.isEmployeeLogin(username, password);

                // Check if the login is successful and user is an admin
                if (isAdmin) {
                    // Show the admin interface with all functionalities
                    initializeAdminInterface();
                } else if (isEmployee) {
                    // Show the employee interface with restricted functionalities
                    initializeEmployeeInterface();
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid username or password!", "Login Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });



        // Set up the frame
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

    }
    
	@SuppressWarnings("unused")
	private static void saveData() {
        DataSerialization.serializeData(Hotel.getInstance());
    }

	
	@SuppressWarnings("unused")
	private static void loadData() {
	    Hotel hotel = (Hotel) DataSerialization.deserializeData();
	    if (hotel != null) {
	        Hotel.setInstance(hotel);
	    } else {
	        // Handle the case where data could not be loaded or does not exist yet
	        // You might create a new instance of Hotel in this case.
	        System.out.println("Data could not be loaded. Creating a new instance of Hotel...");
	        Hotel.getInstance();
	    }
	}
	
	
	
	private void initializeEmployeeInterface() {
	    //isAdmin = false; // Set to false for employee access

        // Set the visibility of menu items for employee here
        addFunctionsMenu.setVisible(true);
        addEmployeeMenuItem.setVisible(false); // Employee cannot add an employee
        addCustomerMenuItem.setVisible(true);
        addBookingMenuItem.setVisible(true);
        addRoomMenuItem.setVisible(true);
        addDepartmentMenuItem.setVisible(false); // Employee cannot add a department
        //setDepartmentManagerMenuItem.setVisible(false); // Employee cannot set department manager
        
        removeFunctionsMenu.setVisible(true);
        removeBookingMenuItem.setVisible(true);
        removeCustomerMenuItem.setVisible(true);
        removeDepartmentMenuItem.setVisible(false); // Employee cannot remove a department
        removeEmployeeMenuItem.setVisible(false); // Employee cannot remove an employee
        removeRoomMenuItem.setVisible(true);
        
        getRealFunctionsMenu.setVisible(true);
        getRealBooking.setVisible(true);
        getRealCustomer.setVisible(true);
        getRealDepartment.setVisible(true);
        getRealEmployee.setVisible(true);
        getRealRoom.setVisible(true);
        
        querieMenu.setVisible(true);
        kemployeesMenuItem.setVisible(true);
        allBookingsByRevenue.setVisible(true);
        allBookingsOfSpecCustomerMenuItem.setVisible(true);
        allCustomersByBookings.setVisible(true);
        allCustomersByPK.setVisible(true);
        totalProfitMenuItem.setVisible(true);
        //loginScreen.setLoggedIn(true);

    }

	private void initializeAdminInterface() {
	    isAdmin = true; // Set to false for employee access

        // Set the visibility of all menu items for admin here
        addFunctionsMenu.setVisible(true);
        addEmployeeMenuItem.setVisible(true);
        addCustomerMenuItem.setVisible(true);
        addBookingMenuItem.setVisible(true);
        addRoomMenuItem.setVisible(true);
        addDepartmentMenuItem.setVisible(true);
        //setDepartmentManagerMenuItem.setVisible(true);
        
        removeFunctionsMenu.setVisible(true);
        removeBookingMenuItem.setVisible(true);
        removeCustomerMenuItem.setVisible(true);
        removeDepartmentMenuItem.setVisible(true);
        removeEmployeeMenuItem.setVisible(true);
        removeRoomMenuItem.setVisible(true);
        
        getRealFunctionsMenu.setVisible(true);
        getRealBooking.setVisible(true);
        getRealCustomer.setVisible(true);
        getRealDepartment.setVisible(true);
        getRealEmployee.setVisible(true);
        getRealRoom.setVisible(true);
        
        querieMenu.setVisible(true);
        kemployeesMenuItem.setVisible(true);
        allBookingsByRevenue.setVisible(true);
        allBookingsOfSpecCustomerMenuItem.setVisible(true);
        allCustomersByBookings.setVisible(true);
        allCustomersByPK.setVisible(true);
        totalProfitMenuItem.setVisible(true);
        //loginScreen.setLoggedIn(true);

    }
	
    private void showErrorMessage(String message) {
        JOptionPane.showMessageDialog(frame, message, "Input Error", JOptionPane.ERROR_MESSAGE);
    }
    
    private void showSuccessMessage(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
    
    

}

class DepartmentRenderer extends DefaultListCellRenderer {
    @Override
    public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

        if (value instanceof Department) {
            Department department = (Department) value;
            // Replace "getDepartmentName()" with the actual method or property you want to display
            setText(department.getDepartmentId());
        }

        return this;
    }
}