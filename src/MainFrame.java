import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.lang.Math.*;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
    private static final int WIDTH = 700;
    private static final int HEIGHT = 400;

    private JTextField textFieldX;
    private JTextField textFieldY;
    private JTextField textFieldZ;
    private JTextField textFieldResult;

    private JTextField textFieldM1;
    private JTextField textFieldM2;
    private JTextField textFieldM3;

    private Double mem1 = 0.0;
    private Double mem2 = 0.0;
    private Double mem3 = 0.0;

    private ButtonGroup radioButtons = new ButtonGroup();
    private Box hboxFormulaType = Box.createHorizontalBox();
    private int formulaId = 1;

    private ButtonGroup variableButtons = new ButtonGroup();
    private int activeVariable = 1; // 1 - Mem1, 2 - Mem2, 3 - Mem3

    public Double calculate1(Double x, Double y, Double z) {
        double lnz = Math.log(abs(x));
        double sinTx = sin(PI * x * x);
        return (sin(y) + y * y + exp(cos(y))) * pow((lnz + sinTx), 1.0 / 4);
    }

    public Double calculate2(Double x, Double y, Double z) {
        double lnz = Math.log(z);
        return (pow((y + pow(x, 3)), 1.0 / z)) / lnz;
    }

    private void addRadioButton(String buttonName, final int formulaId) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(ev -> MainFrame.this.formulaId = formulaId);
        radioButtons.add(button);
        hboxFormulaType.add(button);
    }

    private void addVariableRadioButton(String buttonName, final int variableId, Box container) {
        JRadioButton button = new JRadioButton(buttonName);
        button.addActionListener(ev -> MainFrame.this.activeVariable = variableId);
        variableButtons.add(button);
        container.add(button);
    }

    public MainFrame() {
        super("Вычисление формулы");
        setSize(WIDTH, HEIGHT);
        Toolkit kit = Toolkit.getDefaultToolkit();
        setLocation((kit.getScreenSize().width - WIDTH) / 2,
                (kit.getScreenSize().height - HEIGHT) / 2);

        hboxFormulaType.add(Box.createHorizontalGlue());
        addRadioButton("Формула 1", 1);
        addRadioButton("Формула 2", 2);
        radioButtons.setSelected(radioButtons.getElements().nextElement().getModel(), true);
        hboxFormulaType.add(Box.createHorizontalGlue());
        hboxFormulaType.setBorder(BorderFactory.createLineBorder(Color.YELLOW));

        JLabel labelForX = new JLabel("X:");
        textFieldX = new JTextField("0", 10);
        textFieldX.setMaximumSize(textFieldX.getPreferredSize());

        JLabel labelForY = new JLabel("Y:");
        textFieldY = new JTextField("0", 10);
        textFieldY.setMaximumSize(textFieldY.getPreferredSize());

        JLabel labelForZ = new JLabel("Z:");
        textFieldZ = new JTextField("0", 10);
        textFieldZ.setMaximumSize(textFieldZ.getPreferredSize());

        Box hboxVariables = Box.createHorizontalBox();
        hboxVariables.setBorder(BorderFactory.createLineBorder(Color.RED));
        hboxVariables.add(Box.createHorizontalGlue());
        hboxVariables.add(labelForX);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldX);
        hboxVariables.add(Box.createHorizontalStrut(10)); // Равные расстояния между полями
        hboxVariables.add(labelForY);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldY);
        hboxVariables.add(Box.createHorizontalStrut(10)); // Равные расстояния между полями
        hboxVariables.add(labelForZ);
        hboxVariables.add(Box.createHorizontalStrut(10));
        hboxVariables.add(textFieldZ);
        hboxVariables.add(Box.createHorizontalGlue());

        JLabel labelForResult = new JLabel("Результат:");
        textFieldResult = new JTextField("0", 20);
        textFieldResult.setMaximumSize(textFieldResult.getPreferredSize());

        Box hboxResult = Box.createHorizontalBox();
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.add(labelForResult);
        hboxResult.add(Box.createHorizontalStrut(10));
        hboxResult.add(textFieldResult);
        hboxResult.add(Box.createHorizontalGlue());
        hboxResult.setBorder(BorderFactory.createLineBorder(Color.BLUE));

        Box hboxMemory = Box.createHorizontalBox();
        JPanel memoryPanel = new JPanel();
        memoryPanel.setLayout(new BoxLayout(memoryPanel, BoxLayout.X_AXIS)); // Горизонтальное расположение
        memoryPanel.setBorder(BorderFactory.createLineBorder(Color.RED));
        //memoryPanel.add(Box.createHorizontalGlue());

        JLabel Lableformem1 = new JLabel("Mem1: ");
        Lableformem1.setAlignmentX(Component.LEFT_ALIGNMENT);
        memoryPanel.add(Lableformem1);


        memoryPanel.add(Box.createHorizontalStrut(10)); // Равный отступ
        textFieldM1 = new JTextField(mem1.toString(), 15);
        textFieldM1.setMaximumSize(new java.awt.Dimension(50, 30));// Размер текстового поля
        textFieldM1.setAlignmentX(Component.LEFT_ALIGNMENT);
        memoryPanel.add(textFieldM1);
        memoryPanel.add(Box.createHorizontalGlue());
        //memoryPanel.add(Box.createHorizontalStrut(20));

// Добавляем пространство, чтобы выравнять Mem2 по центру
        //memoryPanel.add(Box.createHorizontalGlue()); // Это создаст пустое пространство перед Mem2

// Добавляем Mem2 в центр
        memoryPanel.add(new JLabel("Mem2:"));
        memoryPanel.add(Box.createHorizontalStrut(10)); // Равный отступ
        textFieldM2 = new JTextField(mem2.toString(), 15);
        textFieldM2.setMaximumSize(new java.awt.Dimension(50, 30)); // Размер текстового поля
        memoryPanel.add(textFieldM2);

// Добавляем пространство после Mem2 для отступа
       // memoryPanel.add(Box.createHorizontalStrut(20));
        memoryPanel.add(Box.createHorizontalGlue());

        memoryPanel.add(new JLabel("Mem3:"));
        memoryPanel.add(Box.createHorizontalStrut(10)); // Равный отступ
        textFieldM3 = new JTextField(mem3.toString(), 15);
        textFieldM3.setMaximumSize(new java.awt.Dimension(50, 30)); // Размер текстового поля
        memoryPanel.add(textFieldM3);
        //memoryPanel.add(Box.createHorizontalStrut(80)); // Отступ после Mem3

        hboxMemory.add(memoryPanel); // Добавляем контейнер с памятью в основной контейнер

        Box hboxVariableSelector = Box.createHorizontalBox();
        hboxVariableSelector.add(Box.createHorizontalGlue());
        addVariableRadioButton("Переменная 1 (Mem1)", 1, hboxVariableSelector);
        addVariableRadioButton("Переменная 2 (Mem2)", 2, hboxVariableSelector);
        addVariableRadioButton("Переменная 3 (Mem3)", 3, hboxVariableSelector);
        variableButtons.setSelected(variableButtons.getElements().nextElement().getModel(), true);
        hboxVariableSelector.add(Box.createHorizontalGlue());

        JButton buttonCalc = new JButton("Вычислить");
        buttonCalc.addActionListener(ev -> {
            try {
                Double x = Double.parseDouble(textFieldX.getText());
                Double y = Double.parseDouble(textFieldY.getText());
                Double z = Double.parseDouble(textFieldZ.getText());
                Double result = (formulaId == 1) ? calculate1(x, y, z) : calculate2(x, y, z);
                textFieldResult.setText(result.toString());
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Ошибка ввода", "Ошибка", JOptionPane.WARNING_MESSAGE);
            }
        });

        JButton buttonMp = new JButton("M+");
        buttonMp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ev) {
                try {
                    Double result = Double.parseDouble(textFieldResult.getText());
                    if (activeVariable == 1) {
                        mem1 += result;
                        textFieldM1.setText(mem1.toString());
                    } else if (activeVariable == 2) {
                        mem2 += result;
                        textFieldM2.setText(mem2.toString());
                    } else if (activeVariable == 3) {
                        mem3 += result;
                        textFieldM3.setText(mem3.toString());
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(MainFrame.this, "Ошибка", "Ошибка", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        JButton buttonMC = new JButton("MC");
        buttonMC.addActionListener(ev -> {
            if (activeVariable == 1) {
                mem1 = 0.0;
                textFieldM1.setText(mem1.toString());
            } else if (activeVariable == 2) {
                mem2 = 0.0;
                textFieldM2.setText(mem2.toString());
            } else if (activeVariable == 3) {
                mem3 = 0.0;
                textFieldM3.setText(mem3.toString());
            }
        });

        // Добавим кнопку "Очистить"
        JButton buttonClear = new JButton("Очистить");
        buttonClear.addActionListener(ev -> {
            // Сброс значений
            textFieldX.setText("0");
            textFieldY.setText("0");
            textFieldZ.setText("0");
            textFieldResult.setText("0");
            mem1 = 0.0;
            mem2 = 0.0;
            mem3 = 0.0;
            textFieldM1.setText(mem1.toString());
            textFieldM2.setText(mem2.toString());
            textFieldM3.setText(mem3.toString());
        });

        Box hboxButtons = Box.createHorizontalBox();
        hboxButtons.add(Box.createHorizontalGlue());
        hboxButtons.add(buttonCalc);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonMp);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonMC);
        hboxButtons.add(Box.createHorizontalStrut(30));
        hboxButtons.add(buttonClear);
        hboxButtons.add(Box.createHorizontalGlue());


        Box hboxMain = Box.createVerticalBox();
        hboxMain.add(hboxFormulaType);
        hboxMain.add(Box.createVerticalStrut(10));
        hboxMain.add(hboxVariables);
        hboxMain.add(Box.createVerticalStrut(10));
        hboxMain.add(hboxResult);
        hboxMain.add(Box.createVerticalStrut(10));
        hboxMain.add(hboxMemory);
        hboxMain.add(Box.createVerticalStrut(10));
        hboxMain.add(hboxVariableSelector);
        hboxMain.add(Box.createVerticalStrut(10));
        hboxMain.add(hboxButtons);

        add(hboxMain);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}
