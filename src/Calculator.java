import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator {
    private JFrame frame;
    private JTextField textField;

    private double firstNumber;
    private String operator;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 400);
        frame.setLayout(new BorderLayout());

        textField = new JTextField();
        textField.setHorizontalAlignment(JTextField.RIGHT);
        frame.add(textField, BorderLayout.NORTH);

        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JMenu functionsMenu = new JMenu("Functions");
        menuBar.add(functionsMenu);

        JMenuItem logMenuItem = new JMenuItem("Logarithm");
        logMenuItem.addActionListener(new FunctionMenuItemClickListener());
        functionsMenu.add(logMenuItem);

        JMenuItem exponentialMenuItem = new JMenuItem("Exponential");
        exponentialMenuItem.addActionListener(new FunctionMenuItemClickListener());
        functionsMenu.add(exponentialMenuItem);

        JMenuItem radianToDegreeMenuItem = new JMenuItem("Radian to Degree");
        radianToDegreeMenuItem.addActionListener(new FunctionMenuItemClickListener());
        functionsMenu.add(radianToDegreeMenuItem);

        JMenuItem powerMenuItem = new JMenuItem("Power");
        powerMenuItem.addActionListener(new FunctionMenuItemClickListener());
        functionsMenu.add(powerMenuItem);

        JMenuItem factorialMenuItem = new JMenuItem("Factorial");
        factorialMenuItem.addActionListener(new FunctionMenuItemClickListener());
        functionsMenu.add(factorialMenuItem);

        JMenuItem absoluteValueMenuItem = new JMenuItem("Absolute Value");
        absoluteValueMenuItem.addActionListener(new FunctionMenuItemClickListener());
        functionsMenu.add(absoluteValueMenuItem);

        JMenu conversionsMenu = new JMenu("Conversions");
        menuBar.add(conversionsMenu);

        JMenuItem temperatureMenuItem = new JMenuItem("Temperature");
        temperatureMenuItem.addActionListener(new ConversionMenuItemClickListener());
        conversionsMenu.add(temperatureMenuItem);

        JMenuItem speedMenuItem = new JMenuItem("Speed");
        speedMenuItem.addActionListener(new ConversionMenuItemClickListener());
        conversionsMenu.add(speedMenuItem);

        JMenuItem distanceMenuItem = new JMenuItem("Distance");
        distanceMenuItem.addActionListener(new ConversionMenuItemClickListener());
        conversionsMenu.add(distanceMenuItem);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(6, 4));

        String[] buttons = {
                "sin", "cos", "tan", "√",
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+"
        };

        for (String button : buttons) {
            JButton jButton = new JButton(button);
            jButton.addActionListener(new ButtonClickListener());
            buttonPanel.add(jButton);
        }

        frame.add(buttonPanel, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String buttonText = ((JButton) event.getSource()).getText();

            switch (buttonText) {
                case "+":
                case "-":
                case "*":
                case "/":
                    operator = buttonText;
                    firstNumber = Double.parseDouble(textField.getText());
                    textField.setText("");
                    break;
                case "=":
                    double secondNumber = Double.parseDouble(textField.getText());
                    double result = calculate(firstNumber, secondNumber, operator);
                    textField.setText(String.valueOf(result));
                    break;
                case "sin":
                    double sinAngle = Double.parseDouble(textField.getText());
                    double sinResult = Math.sin(Math.toRadians(sinAngle));
                    textField.setText(String.valueOf(sinResult));
                    break;
                case "cos":
                    double cosAngle = Double.parseDouble(textField.getText());
                    double cosResult = Math.cos(Math.toRadians(cosAngle));
                    textField.setText(String.valueOf(cosResult));
                    break;
                case "tan":
                    double tanAngle = Double.parseDouble(textField.getText());
                    double tanResult = Math.tan(Math.toRadians(tanAngle));
                    textField.setText(String.valueOf(tanResult));
                    break;
                case "√":
                    double number = Double.parseDouble(textField.getText());
                    double sqrtResult = Math.sqrt(number);
                    textField.setText(String.valueOf(sqrtResult));
                    break;
                default:
                    textField.setText(textField.getText() + buttonText);
                    break;
            }
        }
    }

    private class FunctionMenuItemClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String menuItemText = ((JMenuItem) event.getSource()).getText();

            switch (menuItemText) {
                case "Logarithm":
                    String input = JOptionPane.showInputDialog(frame, "Enter a number:");
                    if (input != null) {
                        double number = Double.parseDouble(input);
                        double logResult = Math.log10(number);
                        textField.setText(String.valueOf(logResult));
                    }
                    break;
                case "Exponential":
                    String inputExponential = JOptionPane.showInputDialog(frame, "Enter a number:");
                    if (inputExponential != null) {
                        double number = Double.parseDouble(inputExponential);
                        double exponentialResult = Math.exp(number);
                        textField.setText(String.valueOf(exponentialResult));
                    }
                    break;
                case "Radian to Degree":
                    String inputRadian = JOptionPane.showInputDialog(frame, "Enter a number in radians:");
                    if (inputRadian != null) {
                        double radians = Double.parseDouble(inputRadian);
                        double degrees = Math.toDegrees(radians);
                        textField.setText(String.valueOf(degrees));
                    }
                    break;
                case "Power":
                    String inputPower = JOptionPane.showInputDialog(frame, "Enter the base:");
                    if (inputPower != null) {
                        double base = Double.parseDouble(inputPower);
                        String inputExponent = JOptionPane.showInputDialog(frame, "Enter the exponent:");
                        if (inputExponent != null) {
                            double exponent = Double.parseDouble(inputExponent);
                            double powerResult = Math.pow(base, exponent);
                            textField.setText(String.valueOf(powerResult));
                        }
                    }
                    break;
                case "Factorial":
                    String inputFactorial = JOptionPane.showInputDialog(frame, "Enter an integer:");
                    if (inputFactorial != null) {
                        int number = Integer.parseInt(inputFactorial);
                        int factorialResult = factorial(number);
                        textField.setText(String.valueOf(factorialResult));
                    }
                    break;
                case "Absolute Value":
                    String inputAbsolute = JOptionPane.showInputDialog(frame, "Enter a number:");
                    if (inputAbsolute != null) {
                        double number = Double.parseDouble(inputAbsolute);
                        double absoluteResult = Math.abs(number);
                        textField.setText(String.valueOf(absoluteResult));
                    }
                    break;
            }
        }
    }

    private class ConversionMenuItemClickListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String menuItemText = ((JMenuItem) event.getSource()).getText();

            switch (menuItemText) {
                case "Temperature":
                    String inputTemp = JOptionPane.showInputDialog(frame, "Enter a temperature value:");
                    if (inputTemp != null) {
                        double temperature = Double.parseDouble(inputTemp);
                        String[] options = {"Celsius to Fahrenheit", "Fahrenheit to Celsius"};
                        int choice = JOptionPane.showOptionDialog(frame, "Select a conversion:", "Temperature Conversion", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                        if (choice == 0) {
                            double result = celsiusToFahrenheit(temperature);
                            textField.setText(String.valueOf(result));
                        } else if (choice == 1) {
                            double result = fahrenheitToCelsius(temperature);
                            textField.setText(String.valueOf(result));
                        }
                    }
                    break;
                case "Speed":
                    String inputSpeed = JOptionPane.showInputDialog(frame, "Enter a speed value:");
                    if (inputSpeed != null) {
                        double speed = Double.parseDouble(inputSpeed);
                        String[] options = {"Kilometers per hour to Miles per hour", "Miles per hour to Kilometers per hour"};
                        int choice = JOptionPane.showOptionDialog(frame, "Select a conversion:", "Speed Conversion", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                        if (choice == 0) {
                            double result = kphToMph(speed);
                            textField.setText(String.valueOf(result));
                        } else if (choice == 1) {
                            double result = mphToKph(speed);
                            textField.setText(String.valueOf(result));
                        }
                    }
                    break;
                case "Distance":
                    String inputDistance = JOptionPane.showInputDialog(frame, "Enter a distance value:");
                    if (inputDistance != null) {
                        double distance = Double.parseDouble(inputDistance);
                        String[] options = {"Miles to Kilometers", "Kilometers to Miles"};
                        int choice = JOptionPane.showOptionDialog(frame, "Select a conversion:", "Distance Conversion", JOptionPane.DEFAULT_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

                        if (choice == 0) {
                            double result = milesToKilometers(distance);
                            textField.setText(String.valueOf(result));
                        } else if (choice == 1) {
                            double result = kilometersToMiles(distance);
                            textField.setText(String.valueOf(result));
                        }
                    }
                    break;
            }
        }
    }

    private double calculate(double firstNumber, double secondNumber, String operator) {
        double result = 0;

        switch (operator) {
            case "+":
                result = firstNumber + secondNumber;
                break;
            case "-":
                result = firstNumber - secondNumber;
                break;
            case "*":
                result = firstNumber * secondNumber;
                break;
            case "/":
                result = firstNumber / secondNumber;
                break;
        }

        return result;
    }

    private int factorial(int number) {
        if (number == 0) {
            return 1;
        } else {
            return number * factorial(number - 1);
        }
    }

    private double celsiusToFahrenheit(double celsius) {
        return celsius * 9 / 5 + 32;
    }

    private double fahrenheitToCelsius(double fahrenheit) {
        return (fahrenheit - 32) * 5 / 9;
    }

    private double kphToMph(double kph) {
        return kph * 0.621371;
    }

    private double mphToKph(double mph) {
        return mph * 1.60934;
    }

    private double milesToKilometers(double miles) {
        return miles * 1.60934;
    }

    private double kilometersToMiles(double kilometers) {
        return kilometers * 0.621371;
    }

    public static void main(String[] args) {
        new Calculator();
    }
}