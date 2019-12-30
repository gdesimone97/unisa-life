package unisagui;

import exam.booklet.Booklet;
import exam.booklet.Subject;
import java.awt.event.KeyEvent;
import javax.swing.SwingUtilities;
import gameSystem.GameManager;
import gameSystem.keySettings.SettingsManager;
import java.awt.Component;
import java.awt.Dialog;
import java.text.ParseException;
import java.util.HashSet;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import saving.SaveManager;
import sound.JukeBoxMusic;
import sound.JukeBoxSound;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

/**
 * @author Virginia Cavallaro
 * @author Davide Coppola
 */
public class GameFrame extends javax.swing.JFrame {

    /**
     * Creates new form GameFrame
     */
    private static GameFrame instance;
    private final static String EMPTY_TEXT = "";
    private String name = "";
    private int avatar = 0;
    private static JukeBoxSound sound = JukeBoxSound.getInstance();
    private static JukeBoxMusic music = JukeBoxMusic.getInstance();
    private static ButtonGroup buttongroup = new javax.swing.ButtonGroup();
    private SettingsManager settings;
    private int moveUp;
    private int moveDown;
    private int moveLeft;
    private int moveRight;
    private int interact;
    private int pause;
    private int map;
    protected DefaultTableModel model;
    private Booklet booklet = Booklet.getInstance();
    private HashSet<Subject> career;
    protected DefaultTableModel careerModel;
    
    
    private GameFrame() {
        initComponents();
        undecoratingDialogs();
        settingLocations(this);
        initialSettings(this);
        initializingTable();
        //saveManager qui
    }

    public static synchronized GameFrame getInstance() {
        if (instance == null) {
            instance = new GameFrame();
        }
        return instance;
    }

    /**
     * this method set all the key settings at the start of the game
     */
    private void setKeyBoard() {
        moveUp = settings.getMoveUp();
        moveDown = settings.getMoveDown();
        moveLeft = settings.getMoveLeft();
        moveRight = settings.getMoveRight();
        interact = settings.getInteractButton();
        pause = settings.getPauseButton();
        map = settings.getMapButton();
        MoveUpField.setText(KeyEvent.getKeyText(moveUp));
        MoveDownField.setText(KeyEvent.getKeyText(moveDown));
        MoveLeftField.setText(KeyEvent.getKeyText(moveLeft));
        MoveRightField.setText(KeyEvent.getKeyText(moveRight));
        InteractField.setText(KeyEvent.getKeyText(interact));
        PauseField.setText(KeyEvent.getKeyText(pause));
        MapField.setText(KeyEvent.getKeyText(map));

    }
    /**
     * this method sets the Booklet of the gui, booklet works with inverse  oolean logic. 
     */
    private void setCareer() {
        int row = 0;
        int column = 0;
        careerModel = (DefaultTableModel) ExamTable.getModel();
        career = booklet.iteratorBooklet();
        for (Subject sub : career) {
            column = 0;
            careerModel.setValueAt(sub.getInfo(), row, column);
            if (booklet.getAvailablity(sub)) {
                careerModel.setValueAt("", row, ++column);
                careerModel.setValueAt("Not passed", row, ++column);
            } else {
                careerModel.setValueAt(booklet.getScore(sub), row, ++column);
                careerModel.setValueAt("Passed", row, ++column);
                row += 1;
            }
        }
        
    }
    /**
     * this method update the Booklet of the gui
     */
    public void updateCareer(){
        SwingUtilities.invokeLater(() -> setCareer());
        
    }


    protected void initializingTable() {
        model = new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Icon"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        InventoryTable.setModel(model);
        InventoryTable.getColumn("Icon").setCellRenderer(new CellRender());
        

    }
    
    class CellRender implements TableCellRenderer {

        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                boolean hasFocus, int row, int column) {
            TableColumn tb = table.getColumn("Icon");
            tb.setMaxWidth(32);
            tb.setMinWidth(32);
            table.setRowHeight(32);
            return (Component) value;
        }

    }

    private void initialSettings(GameFrame instance) {
        SwingUtilities.invokeLater(() -> instance.setVisible(true));
        SwingUtilities.invokeLater(() -> MainMenuDialog.isAlwaysOnTop());
        SwingUtilities.invokeLater(() -> MainMenuDialog.setVisible(true));
        SwingUtilities.invokeLater(() -> instance.validate());

    }

    private void settingAvatarButton() {
        if (MaleBlackButton.isSelected()) {
            MaleBlackButton.setSelected(false);
        }
        if (FemaleBlackButton.isSelected()) {
            FemaleBlackButton.setSelected(false);
        }
        if (FemaleWhiteButton.isSelected()) {
            FemaleWhiteButton.setSelected(false);
        }
        if (MaleWhiteButton.isSelected()) {
            MaleWhiteButton.setSelected(false);
        }
    }
    

    private void undecoratingDialogs() {
        SwingUtilities.invokeLater(() -> MainMenuDialog.setUndecorated(true));
        SwingUtilities.invokeLater(() -> SettingsDialog.setUndecorated(true));
        SwingUtilities.invokeLater(() -> RequestDialog.setUndecorated(true));
        SwingUtilities.invokeLater(() -> ExamDialog.setUndecorated(true));
        SwingUtilities.invokeLater(() -> AvatarChooserDialog.setUndecorated(true));
        SwingUtilities.invokeLater(() -> InventoryDialog.setUndecorated(true));
        SwingUtilities.invokeLater(() -> ConvDialog.setUndecorated(true));
        SwingUtilities.invokeLater(() -> ConvDialog.setAlwaysOnTop(true));
        SwingUtilities.invokeLater(() -> HintDialog.setUndecorated(true));
        SwingUtilities.invokeLater(() -> HintDialog.setAlwaysOnTop(true));
        SwingUtilities.invokeLater(() -> QuestDialog.setUndecorated(true));
        SwingUtilities.invokeLater(() -> KeyboardSettingsDialog.setUndecorated(true));
        SwingUtilities.invokeLater(() -> CareerDialog.setUndecorated(true));
    }

    private void settingLocations(GameFrame instance) {
        SwingUtilities.invokeLater(() -> MainMenuDialog.setLocation(instance.getLocation()));
        SwingUtilities.invokeLater(() -> SettingsDialog.setLocation(instance.getLocation()));
        SwingUtilities.invokeLater(() -> KeyboardSettingsDialog.setLocation(instance.getLocation()));
        SwingUtilities.invokeLater(() -> RequestDialog.setLocation(instance.getLocation().x + 175, instance.getLocation().y + 200));
        SwingUtilities.invokeLater(() -> ExamDialog.setLocation(instance.getLocation().x + 50, instance.getLocation().y + 75));
        SwingUtilities.invokeLater(() -> AvatarChooserDialog.setLocation(instance.getLocation()));
        SwingUtilities.invokeLater(() -> InventoryDialog.setLocation(instance.getLocation()));
        SwingUtilities.invokeLater(() -> HintDialog.setLocation(instance.getLocation().x + 200, instance.getLocation().y + 275));
        SwingUtilities.invokeLater(() -> ConvDialog.setLocation(instance.getLocation().x + 112, instance.getLocation().y + 450));
        SwingUtilities.invokeLater(() -> QuestDialog.setLocation(instance.getLocation().x + 90, instance.getLocation().y + 125));
        SwingUtilities.invokeLater(() -> InventoryDialog.setLocation(instance.getLocation().x + 50, instance.getLocation().y + 75));
        SwingUtilities.invokeLater(() -> CareerDialog.setLocation(instance.getLocation().x + 50, instance.getLocation().y + 75));
    }

    protected void settingLanguage(String s) throws Exception {
        //in cui verranno settati testi e cazzi e mazzi nella lingua scelta
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ExamDialog = new javax.swing.JDialog();
        ExamPanel = new javax.swing.JPanel();
        ProfLabel = new javax.swing.JLabel();
        StudentLabel = new javax.swing.JLabel();
        NameOfExamLabel = new javax.swing.JLabel();
        LevelOfQuestionLabel = new javax.swing.JLabel();
        ExamScrollPane = new javax.swing.JScrollPane();
        ExamTextArea = new javax.swing.JTextArea();
        FirstAnswer = new javax.swing.JButton();
        SecondAnswer = new javax.swing.JButton();
        ThirdAnswer = new javax.swing.JButton();
        FourthAnswer = new javax.swing.JButton();
        ConfirmAnswer = new javax.swing.JButton();
        TimeLabel1 = new javax.swing.JLabel();
        MainMenuDialog = new javax.swing.JDialog((Dialog)null);
        MainMenuPanel = new javax.swing.JPanel();
        MainMenuLabel = new javax.swing.JLabel();
        SettingsButton = new javax.swing.JButton();
        ExitButton = new javax.swing.JButton();
        NewGameButton = new javax.swing.JButton();
        ResumeGameButton = new javax.swing.JButton();
        CreditsButton = new javax.swing.JButton();
        SettingsDialog = new javax.swing.JDialog();
        SettingsPanel = new javax.swing.JPanel();
        SettingsLayer = new javax.swing.JLabel();
        SoundButton = new javax.swing.JButton();
        MusicButton = new javax.swing.JButton();
        KeyboardButton = new javax.swing.JButton();
        ReturnToMainMenuButton = new javax.swing.JButton();
        LanguageComboBox = new javax.swing.JComboBox<>();
        SettingsCloseButton = new javax.swing.JButton();
        RequestDialog = new javax.swing.JDialog();
        RequestPanel = new javax.swing.JPanel();
        YesButton = new javax.swing.JButton();
        NoButton = new javax.swing.JButton();
        RequestScrollPane = new javax.swing.JScrollPane();
        RequestLabel = new javax.swing.JTextArea();
        CareerDialog = new javax.swing.JDialog();
        CareerPanel = new javax.swing.JPanel();
        CareerScrollPane = new javax.swing.JScrollPane();
        ExamTable = new javax.swing.JTable();
        CareerLabel = new javax.swing.JLabel();
        ExitCareerDialog = new javax.swing.JLabel();
        AvatarChooserDialog = new javax.swing.JDialog((Dialog)null);
        AvatarChooserPanel = new javax.swing.JPanel();
        AvatarName = new javax.swing.JTextField();
        AvatarNameLabel = new javax.swing.JLabel();
        MaleWhiteButton = new javax.swing.JButton();
        AvatarImage = new javax.swing.JLabel();
        MaleBlackButton = new javax.swing.JButton();
        FemaleBlackButton = new javax.swing.JButton();
        FemaleWhiteButton = new javax.swing.JButton();
        AvatarIcon = new javax.swing.JLabel();
        AvatarOkButton = new javax.swing.JButton();
        KeyboardSettingsDialog = new javax.swing.JDialog();
        KeyBoardPanel = new javax.swing.JPanel();
        KeyboardSettingsLabel = new javax.swing.JLabel();
        MoveUpLabel = new javax.swing.JLabel();
        MoveDownLabel = new javax.swing.JLabel();
        MoveLeftLabel = new javax.swing.JLabel();
        MoveRightLabel = new javax.swing.JLabel();
        InteractLabel = new javax.swing.JLabel();
        PauseLabel = new javax.swing.JLabel();
        MapLabel = new javax.swing.JLabel();
        OpenInventoryField = new javax.swing.JFormattedTextField();
        OpenInventoryLabel = new javax.swing.JLabel();
        KeyboardSettingsCloseButton = new javax.swing.JButton();
        MoveUpField = new javax.swing.JFormattedTextField();
        MoveDownField = new javax.swing.JFormattedTextField();
        MoveLeftField = new javax.swing.JFormattedTextField();
        MoveRightField = new javax.swing.JFormattedTextField();
        InteractField = new javax.swing.JFormattedTextField();
        PauseField = new javax.swing.JFormattedTextField();
        MapField = new javax.swing.JFormattedTextField();
        ConvDialog = new javax.swing.JDialog();
        ConversationScrollPane = new javax.swing.JScrollPane();
        ConversationTextArea = new javax.swing.JTextArea();
        HintDialog = new javax.swing.JDialog();
        HintScrollPane = new javax.swing.JScrollPane();
        HintTextArea = new javax.swing.JTextArea();
        QuestDialog = new javax.swing.JDialog();
        QuestPanel = new javax.swing.JPanel();
        QuestListScrollPane = new javax.swing.JScrollPane();
        QuestList = new javax.swing.JList<>();
        QuestTextScrollPane = new javax.swing.JScrollPane();
        QuestTextArea = new javax.swing.JTextArea();
        ExitQuestDialogLabel = new javax.swing.JLabel();
        InventoryDialog = new javax.swing.JDialog();
        InventoryPanel = new javax.swing.JPanel();
        InventoryLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        InventoryTable = new javax.swing.JTable();
        ExitInventoryDialog = new javax.swing.JLabel();
        HudPanel = new javax.swing.JPanel();
        LevelLabel = new javax.swing.JLabel();
        MoneyIcon = new javax.swing.JLabel();
        MoneyLabel = new javax.swing.JLabel();
        EnergyProgressBar = new javax.swing.JProgressBar();
        EnergyIcon = new javax.swing.JLabel();
        StressIcon = new javax.swing.JLabel();
        HungerIcon = new javax.swing.JLabel();
        StressProgressBar = new javax.swing.JProgressBar();
        HungerProgressBar = new javax.swing.JProgressBar();
        SettingsButtonFrame = new javax.swing.JButton();
        QuestButtonFrame = new javax.swing.JButton();
        InventoryButtonFrame = new javax.swing.JButton();
        CareerButtonFrame1 = new javax.swing.JButton();
        RightBorder = new javax.swing.JPanel();
        GameCloseButton = new javax.swing.JButton();
        LeftBorder = new javax.swing.JPanel();
        UpperBorder = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        ExamDialog.setMinimumSize(new java.awt.Dimension(500, 500));
        ExamDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        ExamDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowDeactivated(java.awt.event.WindowEvent evt) {
                ExamDialogWindowDeactivated(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                ExamDialogWindowOpened(evt);
            }
        });

        ExamPanel.setBackground(new java.awt.Color(93, 150, 199));
        ExamPanel.setMaximumSize(new java.awt.Dimension(500, 500));
        ExamPanel.setMinimumSize(new java.awt.Dimension(500, 500));
        ExamPanel.setPreferredSize(new java.awt.Dimension(500, 500));

        ProfLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/foggiaexam75ok.png"))); // NOI18N
        ProfLabel.setText("jLabel1");
        ProfLabel.setMaximumSize(new java.awt.Dimension(75, 75));
        ProfLabel.setMinimumSize(new java.awt.Dimension(75, 75));
        ProfLabel.setPreferredSize(new java.awt.Dimension(75, 75));

        StudentLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/man75.png"))); // NOI18N

        NameOfExamLabel.setFont(new java.awt.Font("Arial Black", 1, 13)); // NOI18N
        NameOfExamLabel.setForeground(new java.awt.Color(255, 255, 255));
        NameOfExamLabel.setText("ExamName");
        NameOfExamLabel.setMaximumSize(new java.awt.Dimension(125, 25));
        NameOfExamLabel.setMinimumSize(new java.awt.Dimension(125, 25));
        NameOfExamLabel.setPreferredSize(new java.awt.Dimension(125, 25));

        LevelOfQuestionLabel.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        LevelOfQuestionLabel.setForeground(new java.awt.Color(255, 255, 255));
        LevelOfQuestionLabel.setText("LevelOfQuestion");
        LevelOfQuestionLabel.setMaximumSize(new java.awt.Dimension(125, 25));
        LevelOfQuestionLabel.setMinimumSize(new java.awt.Dimension(125, 25));
        LevelOfQuestionLabel.setPreferredSize(new java.awt.Dimension(125, 25));

        TimeLabel.setBackground(new java.awt.Color(51, 51, 255));
        TimeLabel.setMaximumSize(new java.awt.Dimension(75, 25));
        TimeLabel.setMinimumSize(new java.awt.Dimension(75, 25));
        TimeLabel.setPreferredSize(new java.awt.Dimension(75, 25));

        ExamScrollPane.setHorizontalScrollBar(null);

        ExamTextArea.setEditable(false);
        ExamTextArea.setColumns(8);
        ExamTextArea.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        ExamTextArea.setLineWrap(true);
        ExamTextArea.setRows(5);
        ExamTextArea.setWrapStyleWord(true);
        ExamTextArea.setMaximumSize(new java.awt.Dimension(300, 100));
        ExamTextArea.setMinimumSize(new java.awt.Dimension(300, 100));
        ExamScrollPane.setViewportView(ExamTextArea);

        FirstAnswer.setBackground(new java.awt.Color(75, 125, 167));
        FirstAnswer.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        FirstAnswer.setForeground(new java.awt.Color(255, 255, 255));
        FirstAnswer.setBorder(null);
        FirstAnswer.setMaximumSize(new java.awt.Dimension(300, 25));
        FirstAnswer.setMinimumSize(new java.awt.Dimension(300, 25));
        FirstAnswer.setPreferredSize(new java.awt.Dimension(300, 25));
        FirstAnswer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FirstAnswerMouseClicked(evt);
            }
        });

        SecondAnswer.setBackground(new java.awt.Color(75, 125, 167));
        SecondAnswer.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        SecondAnswer.setForeground(new java.awt.Color(255, 255, 255));
        SecondAnswer.setBorder(null);
        SecondAnswer.setMaximumSize(new java.awt.Dimension(300, 25));
        SecondAnswer.setMinimumSize(new java.awt.Dimension(300, 25));
        SecondAnswer.setPreferredSize(new java.awt.Dimension(300, 25));
        SecondAnswer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SecondAnswerMouseClicked(evt);
            }
        });

        ThirdAnswer.setBackground(new java.awt.Color(75, 125, 167));
        ThirdAnswer.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        ThirdAnswer.setForeground(new java.awt.Color(255, 255, 255));
        ThirdAnswer.setBorder(null);
        ThirdAnswer.setMaximumSize(new java.awt.Dimension(300, 25));
        ThirdAnswer.setMinimumSize(new java.awt.Dimension(300, 25));
        ThirdAnswer.setPreferredSize(new java.awt.Dimension(300, 25));
        ThirdAnswer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ThirdAnswerMouseClicked(evt);
            }
        });

        FourthAnswer.setBackground(new java.awt.Color(75, 125, 167));
        FourthAnswer.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        FourthAnswer.setForeground(new java.awt.Color(255, 255, 255));
        FourthAnswer.setBorder(null);
        FourthAnswer.setMaximumSize(new java.awt.Dimension(300, 25));
        FourthAnswer.setMinimumSize(new java.awt.Dimension(300, 25));
        FourthAnswer.setPreferredSize(new java.awt.Dimension(300, 25));
        FourthAnswer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                FourthAnswerMouseClicked(evt);
            }
        });

        ConfirmAnswer.setBackground(new java.awt.Color(75, 125, 167));
        ConfirmAnswer.setFont(new java.awt.Font("Arial Black", 0, 13)); // NOI18N
        ConfirmAnswer.setForeground(new java.awt.Color(255, 255, 255));
        ConfirmAnswer.setText("Next Question");
        ConfirmAnswer.setBorder(null);
        ConfirmAnswer.setMaximumSize(new java.awt.Dimension(125, 25));
        ConfirmAnswer.setMinimumSize(new java.awt.Dimension(125, 25));
        ConfirmAnswer.setPreferredSize(new java.awt.Dimension(125, 25));
        ConfirmAnswer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConfirmAnswerActionPerformed(evt);
            }
        });

        TimeLabel1.setForeground(new java.awt.Color(255, 255, 255));
        TimeLabel1.setText("0");

        javax.swing.GroupLayout ExamPanelLayout = new javax.swing.GroupLayout(ExamPanel);
        ExamPanel.setLayout(ExamPanelLayout);
        ExamPanelLayout.setHorizontalGroup(
            ExamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExamPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(ExamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(FirstAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ExamPanelLayout.createSequentialGroup()
                        .addComponent(ProfLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addGroup(ExamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(ExamPanelLayout.createSequentialGroup()
                                .addComponent(NameOfExamLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(LevelOfQuestionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(ExamScrollPane)))
                    .addComponent(SecondAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ThirdAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(ExamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(ExamPanelLayout.createSequentialGroup()
                            .addComponent(TimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(ConfirmAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(FourthAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(StudentLabel))
        );
        ExamPanelLayout.setVerticalGroup(
            ExamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ExamPanelLayout.createSequentialGroup()
                .addGroup(ExamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ExamPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(ProfLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ExamPanelLayout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addGroup(ExamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(NameOfExamLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(LevelOfQuestionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(ExamScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25)
                .addComponent(FirstAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(SecondAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(ThirdAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(FourthAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(ExamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ExamPanelLayout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(StudentLabel))
                    .addGroup(ExamPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(ExamPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ConfirmAnswer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TimeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
        );

        ConfirmAnswer.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout ExamDialogLayout = new javax.swing.GroupLayout(ExamDialog.getContentPane());
        ExamDialog.getContentPane().setLayout(ExamDialogLayout);
        ExamDialogLayout.setHorizontalGroup(
            ExamDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(ExamDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ExamDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ExamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        ExamDialogLayout.setVerticalGroup(
            ExamDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(ExamDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(ExamDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(ExamPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        MainMenuDialog.setMinimumSize(new java.awt.Dimension(600, 750));
        MainMenuDialog.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                MainMenuDialogComponentShown(evt);
            }
        });

        MainMenuPanel.setBackground(new java.awt.Color(93, 150, 199));
        MainMenuPanel.setMaximumSize(new java.awt.Dimension(600, 750));
        MainMenuPanel.setMinimumSize(new java.awt.Dimension(600, 750));

        MainMenuLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MAIN MENU AZZURRINO.png"))); // NOI18N
        MainMenuLabel.setText("                   MAIN MENU");
        MainMenuLabel.setMaximumSize(new java.awt.Dimension(200, 75));
        MainMenuLabel.setMinimumSize(new java.awt.Dimension(200, 75));
        MainMenuLabel.setPreferredSize(new java.awt.Dimension(200, 75));

        SettingsButton.setBackground(new java.awt.Color(93, 150, 199));
        SettingsButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/settings75.png"))); // NOI18N
        SettingsButton.setBorder(null);
        SettingsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsButtonActionPerformed(evt);
            }
        });

        ExitButton.setBackground(new java.awt.Color(93, 150, 199));
        ExitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/EXIT75.png"))); // NOI18N
        ExitButton.setBorder(null);
        ExitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExitButtonActionPerformed(evt);
            }
        });

        NewGameButton.setBackground(new java.awt.Color(75, 125, 167));
        NewGameButton.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        NewGameButton.setForeground(new java.awt.Color(255, 255, 255));
        NewGameButton.setText("NEW GAME");
        NewGameButton.setBorder(null);
        NewGameButton.setMaximumSize(new java.awt.Dimension(200, 50));
        NewGameButton.setMinimumSize(new java.awt.Dimension(200, 50));
        NewGameButton.setPreferredSize(new java.awt.Dimension(200, 50));
        NewGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NewGameButtonActionPerformed(evt);
            }
        });

        ResumeGameButton.setBackground(new java.awt.Color(75, 125, 167));
        ResumeGameButton.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        ResumeGameButton.setForeground(new java.awt.Color(255, 255, 255));
        ResumeGameButton.setText("RESUME GAME");
        ResumeGameButton.setBorder(null);
        ResumeGameButton.setEnabled(false);
        ResumeGameButton.setMaximumSize(new java.awt.Dimension(200, 50));
        ResumeGameButton.setMinimumSize(new java.awt.Dimension(200, 50));
        ResumeGameButton.setPreferredSize(new java.awt.Dimension(200, 50));
        ResumeGameButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResumeGameButtonActionPerformed(evt);
            }
        });

        CreditsButton.setBackground(new java.awt.Color(75, 125, 167));
        CreditsButton.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        CreditsButton.setForeground(new java.awt.Color(255, 255, 255));
        CreditsButton.setText("CREDITS");
        CreditsButton.setBorder(null);
        CreditsButton.setEnabled(false);
        CreditsButton.setMaximumSize(new java.awt.Dimension(200, 50));
        CreditsButton.setMinimumSize(new java.awt.Dimension(200, 50));
        CreditsButton.setPreferredSize(new java.awt.Dimension(200, 50));
        CreditsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CreditsButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout MainMenuPanelLayout = new javax.swing.GroupLayout(MainMenuPanel);
        MainMenuPanel.setLayout(MainMenuPanelLayout);
        MainMenuPanelLayout.setHorizontalGroup(
            MainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainMenuPanelLayout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addGroup(MainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(MainMenuPanelLayout.createSequentialGroup()
                        .addComponent(SettingsButton)
                        .addGap(50, 50, 50)
                        .addComponent(ExitButton))
                    .addComponent(NewGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(MainMenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ResumeGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CreditsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(200, 200, 200))
        );
        MainMenuPanelLayout.setVerticalGroup(
            MainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(MainMenuPanelLayout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(MainMenuLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(NewGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(ResumeGameButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(MainMenuPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SettingsButton)
                    .addComponent(ExitButton))
                .addGap(25, 25, 25)
                .addComponent(CreditsButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(175, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout MainMenuDialogLayout = new javax.swing.GroupLayout(MainMenuDialog.getContentPane());
        MainMenuDialog.getContentPane().setLayout(MainMenuDialogLayout);
        MainMenuDialogLayout.setHorizontalGroup(
            MainMenuDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
            .addGroup(MainMenuDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MainMenuDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(MainMenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        MainMenuDialogLayout.setVerticalGroup(
            MainMenuDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
            .addGroup(MainMenuDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(MainMenuDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(MainMenuPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        SettingsDialog.setMinimumSize(new java.awt.Dimension(600, 750));

        SettingsPanel.setBackground(new java.awt.Color(93, 150, 199));
        SettingsPanel.setMaximumSize(new java.awt.Dimension(600, 750));
        SettingsPanel.setMinimumSize(new java.awt.Dimension(600, 750));

        SettingsLayer.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        SettingsLayer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/SETTINGS AZZURRINO.png"))); // NOI18N

        SoundButton.setBackground(new java.awt.Color(93, 150, 199));
        SoundButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/SOUND.png"))); // NOI18N
        SoundButton.setToolTipText("");
        SoundButton.setBorder(null);
        SoundButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SoundButtonActionPerformed(evt);
            }
        });

        MusicButton.setBackground(new java.awt.Color(93, 150, 199));
        MusicButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MUSIC.png"))); // NOI18N
        MusicButton.setBorder(null);
        MusicButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MusicButtonActionPerformed(evt);
            }
        });

        KeyboardButton.setBackground(new java.awt.Color(75, 125, 167));
        KeyboardButton.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        KeyboardButton.setForeground(new java.awt.Color(255, 255, 255));
        KeyboardButton.setText("KEYBOARD SETTINGS");
        KeyboardButton.setBorder(null);
        KeyboardButton.setMaximumSize(new java.awt.Dimension(200, 50));
        KeyboardButton.setMinimumSize(new java.awt.Dimension(200, 50));
        KeyboardButton.setPreferredSize(new java.awt.Dimension(200, 50));
        KeyboardButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeyboardButtonActionPerformed(evt);
            }
        });

        ReturnToMainMenuButton.setBackground(new java.awt.Color(75, 125, 167));
        ReturnToMainMenuButton.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        ReturnToMainMenuButton.setForeground(new java.awt.Color(255, 255, 255));
        ReturnToMainMenuButton.setText("MAIN MENU");
        ReturnToMainMenuButton.setBorder(null);
        ReturnToMainMenuButton.setMaximumSize(new java.awt.Dimension(200, 50));
        ReturnToMainMenuButton.setMinimumSize(new java.awt.Dimension(200, 50));
        ReturnToMainMenuButton.setPreferredSize(new java.awt.Dimension(200, 50));
        ReturnToMainMenuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReturnToMainMenuButtonActionPerformed(evt);
            }
        });

        LanguageComboBox.setBackground(new java.awt.Color(75, 125, 167));
        LanguageComboBox.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        LanguageComboBox.setForeground(new java.awt.Color(255, 255, 255));
        LanguageComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "English" }));
        LanguageComboBox.setMaximumSize(new java.awt.Dimension(200, 50));
        LanguageComboBox.setMinimumSize(new java.awt.Dimension(200, 50));
        LanguageComboBox.setPreferredSize(new java.awt.Dimension(200, 50));
        LanguageComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LanguageComboBoxActionPerformed(evt);
            }
        });

        SettingsCloseButton.setBackground(new java.awt.Color(93, 150, 199));
        SettingsCloseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/exitbutton24.png"))); // NOI18N
        SettingsCloseButton.setBorder(null);
        SettingsCloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsCloseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout SettingsPanelLayout = new javax.swing.GroupLayout(SettingsPanel);
        SettingsPanel.setLayout(SettingsPanelLayout);
        SettingsPanelLayout.setHorizontalGroup(
            SettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SettingsPanelLayout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addGroup(SettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(LanguageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(SettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(SettingsPanelLayout.createSequentialGroup()
                            .addComponent(SoundButton)
                            .addGap(50, 50, 50)
                            .addComponent(MusicButton))
                        .addComponent(SettingsLayer)
                        .addComponent(KeyboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(ReturnToMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(200, 200, 200))
            .addComponent(SettingsCloseButton, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        SettingsPanelLayout.setVerticalGroup(
            SettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(SettingsPanelLayout.createSequentialGroup()
                .addComponent(SettingsCloseButton)
                .addGap(150, 150, 150)
                .addComponent(SettingsLayer)
                .addGap(25, 25, 25)
                .addGroup(SettingsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SoundButton)
                    .addComponent(MusicButton))
                .addGap(25, 25, 25)
                .addComponent(LanguageComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(KeyboardButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(ReturnToMainMenuButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(175, 175, 175))
        );

        javax.swing.GroupLayout SettingsDialogLayout = new javax.swing.GroupLayout(SettingsDialog.getContentPane());
        SettingsDialog.getContentPane().setLayout(SettingsDialogLayout);
        SettingsDialogLayout.setHorizontalGroup(
            SettingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(SettingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(SettingsDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(SettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        SettingsDialogLayout.setVerticalGroup(
            SettingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
            .addGroup(SettingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(SettingsDialogLayout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(SettingsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        RequestDialog.setAlwaysOnTop(true);
        RequestDialog.setMinimumSize(new java.awt.Dimension(250, 175));
        RequestDialog.setModalExclusionType(java.awt.Dialog.ModalExclusionType.APPLICATION_EXCLUDE);
        RequestDialog.setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        RequestDialog.setType(java.awt.Window.Type.POPUP);
        RequestDialog.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                RequestDialogWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
            }
        });

        RequestPanel.setBackground(new java.awt.Color(93, 150, 199));
        RequestPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(254, 215, 102)));
        RequestPanel.setMaximumSize(new java.awt.Dimension(250, 175));
        RequestPanel.setMinimumSize(new java.awt.Dimension(250, 175));
        RequestPanel.setPreferredSize(new java.awt.Dimension(250, 175));

        YesButton.setBackground(new java.awt.Color(93, 150, 199));
        YesButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/confirm50.png"))); // NOI18N
        YesButton.setBorder(null);
        YesButton.setPreferredSize(new java.awt.Dimension(75, 50));
        YesButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                YesButtonActionPerformed(evt);
            }
        });

        NoButton.setBackground(new java.awt.Color(93, 150, 199));
        NoButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/cancel50.png"))); // NOI18N
        NoButton.setBorder(null);
        NoButton.setPreferredSize(new java.awt.Dimension(75, 50));
        NoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NoButtonActionPerformed(evt);
            }
        });

        RequestLabel.setEditable(false);
        RequestLabel.setBackground(new java.awt.Color(209, 250, 255));
        RequestLabel.setColumns(20);
        RequestLabel.setFont(new java.awt.Font("Courier New", 0, 14)); // NOI18N
        RequestLabel.setLineWrap(true);
        RequestLabel.setRows(5);
        RequestLabel.setToolTipText("");
        RequestLabel.setWrapStyleWord(true);
        RequestLabel.setBorder(null);
        RequestScrollPane.setViewportView(RequestLabel);

        javax.swing.GroupLayout RequestPanelLayout = new javax.swing.GroupLayout(RequestPanel);
        RequestPanel.setLayout(RequestPanelLayout);
        RequestPanelLayout.setHorizontalGroup(
            RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RequestPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(RequestScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(RequestPanelLayout.createSequentialGroup()
                        .addComponent(YesButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 48, Short.MAX_VALUE)
                        .addComponent(NoButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(25, 25, 25))
        );
        RequestPanelLayout.setVerticalGroup(
            RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RequestPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(RequestScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(RequestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(NoButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(YesButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout RequestDialogLayout = new javax.swing.GroupLayout(RequestDialog.getContentPane());
        RequestDialog.getContentPane().setLayout(RequestDialogLayout);
        RequestDialogLayout.setHorizontalGroup(
            RequestDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RequestDialogLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(RequestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        RequestDialogLayout.setVerticalGroup(
            RequestDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RequestDialogLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(RequestPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        CareerDialog.setMinimumSize(new java.awt.Dimension(500, 500));
        CareerDialog.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                CareerDialogWindowLostFocus(evt);
            }
        });
        CareerDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                CareerDialogWindowOpened(evt);
            }
        });

        CareerPanel.setBackground(new java.awt.Color(93, 150, 199));
        CareerPanel.setMaximumSize(new java.awt.Dimension(500, 500));
        CareerPanel.setMinimumSize(new java.awt.Dimension(500, 500));
        CareerPanel.setPreferredSize(new java.awt.Dimension(500, 500));

        ExamTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        ExamTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Exam", "Vote", "State"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        ExamTable.setMaximumSize(new java.awt.Dimension(450, 400));
        ExamTable.setMinimumSize(new java.awt.Dimension(450, 400));
        ExamTable.setPreferredSize(new java.awt.Dimension(450, 400));
        ExamTable.setRequestFocusEnabled(false);
        ExamTable.getTableHeader().setReorderingAllowed(false);
        CareerScrollPane.setViewportView(ExamTable);
        if (ExamTable.getColumnModel().getColumnCount() > 0) {
            ExamTable.getColumnModel().getColumn(0).setResizable(false);
            ExamTable.getColumnModel().getColumn(0).setPreferredWidth(300);
            ExamTable.getColumnModel().getColumn(1).setResizable(false);
            ExamTable.getColumnModel().getColumn(1).setPreferredWidth(50);
            ExamTable.getColumnModel().getColumn(2).setResizable(false);
            ExamTable.getColumnModel().getColumn(2).setPreferredWidth(100);
        }

        CareerLabel.setForeground(new java.awt.Color(204, 255, 255));
        CareerLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/BOOKLET.png"))); // NOI18N
        CareerLabel.setText("X");
        CareerLabel.setMaximumSize(new java.awt.Dimension(100, 50));
        CareerLabel.setMinimumSize(new java.awt.Dimension(100, 50));
        CareerLabel.setPreferredSize(new java.awt.Dimension(100, 50));

        ExitCareerDialog.setForeground(new java.awt.Color(204, 255, 255));
        ExitCareerDialog.setText("X");
        ExitCareerDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitCareerDialogMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout CareerPanelLayout = new javax.swing.GroupLayout(CareerPanel);
        CareerPanel.setLayout(CareerPanelLayout);
        CareerPanelLayout.setHorizontalGroup(
            CareerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CareerPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(CareerScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CareerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CareerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(182, 182, 182)
                .addComponent(ExitCareerDialog, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        CareerPanelLayout.setVerticalGroup(
            CareerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CareerPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(CareerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(CareerLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ExitCareerDialog))
                .addComponent(CareerScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26))
        );

        javax.swing.GroupLayout CareerDialogLayout = new javax.swing.GroupLayout(CareerDialog.getContentPane());
        CareerDialog.getContentPane().setLayout(CareerDialogLayout);
        CareerDialogLayout.setHorizontalGroup(
            CareerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CareerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        CareerDialogLayout.setVerticalGroup(
            CareerDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(CareerPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        AvatarChooserDialog.setMinimumSize(new java.awt.Dimension(600, 750));

        AvatarChooserPanel.setBackground(new java.awt.Color(93, 150, 199));
        AvatarChooserPanel.setMaximumSize(new java.awt.Dimension(600, 750));
        AvatarChooserPanel.setMinimumSize(new java.awt.Dimension(600, 750));
        AvatarChooserPanel.setPreferredSize(new java.awt.Dimension(600, 750));
        AvatarChooserPanel.setRequestFocusEnabled(false);

        AvatarName.setFont(new java.awt.Font("Arial Black", 0, 16)); // NOI18N
        AvatarName.setForeground(new java.awt.Color(93, 150, 199));
        AvatarName.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        AvatarName.setMaximumSize(new java.awt.Dimension(200, 50));
        AvatarName.setMinimumSize(new java.awt.Dimension(200, 50));
        AvatarName.setPreferredSize(new java.awt.Dimension(200, 50));

        AvatarNameLabel.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        AvatarNameLabel.setForeground(new java.awt.Color(255, 255, 255));
        AvatarNameLabel.setText("Your name:");
        AvatarNameLabel.setMaximumSize(new java.awt.Dimension(200, 25));
        AvatarNameLabel.setMinimumSize(new java.awt.Dimension(200, 25));
        AvatarNameLabel.setPreferredSize(new java.awt.Dimension(200, 25));

        MaleWhiteButton.setBackground(new java.awt.Color(93, 150, 199));
        MaleWhiteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/man75.png"))); // NOI18N
        MaleWhiteButton.setBorder(null);
        buttongroup.add(MaleWhiteButton);
        MaleWhiteButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/man75pressed.png"))); // NOI18N
        MaleWhiteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaleWhiteButtonActionPerformed(evt);
            }
        });

        AvatarImage.setMaximumSize(new java.awt.Dimension(200, 200));
        AvatarImage.setMinimumSize(new java.awt.Dimension(200, 200));
        AvatarImage.setName(""); // NOI18N
        AvatarImage.setPreferredSize(new java.awt.Dimension(200, 200));

        MaleBlackButton.setBackground(new java.awt.Color(93, 150, 199));
        MaleBlackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/BLACKman75.png"))); // NOI18N
        MaleBlackButton.setBorder(null);
        buttongroup.add(MaleBlackButton);
        MaleBlackButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/BLACKman75pressed.png"))); // NOI18N
        MaleBlackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MaleBlackButtonActionPerformed(evt);
            }
        });

        FemaleBlackButton.setBackground(new java.awt.Color(93, 150, 199));
        FemaleBlackButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/BLACKwoman75.png"))); // NOI18N
        FemaleBlackButton.setBorder(null);
        buttongroup.add(FemaleBlackButton);
        FemaleBlackButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/BLACKwoman75pressed.png"))); // NOI18N
        FemaleBlackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FemaleBlackButtonActionPerformed(evt);
            }
        });

        FemaleWhiteButton.setBackground(new java.awt.Color(93, 150, 199));
        FemaleWhiteButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/woman75.png"))); // NOI18N
        FemaleWhiteButton.setBorder(null);
        buttongroup.add(FemaleWhiteButton);
        FemaleWhiteButton.setSelectedIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/woman75pressed.png"))); // NOI18N
        FemaleWhiteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FemaleWhiteButtonActionPerformed(evt);
            }
        });

        AvatarIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/AVATAR AZZURRINO.png"))); // NOI18N
        AvatarIcon.setText("                 AVATAR");
        AvatarIcon.setMaximumSize(new java.awt.Dimension(200, 75));
        AvatarIcon.setMinimumSize(new java.awt.Dimension(200, 75));
        AvatarIcon.setPreferredSize(new java.awt.Dimension(200, 75));

        AvatarOkButton.setBackground(new java.awt.Color(75, 125, 167));
        AvatarOkButton.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        AvatarOkButton.setForeground(new java.awt.Color(255, 255, 255));
        AvatarOkButton.setText("OK");
        AvatarOkButton.setBorder(null);
        AvatarOkButton.setEnabled(false);
        AvatarOkButton.setMaximumSize(new java.awt.Dimension(75, 50));
        AvatarOkButton.setMinimumSize(new java.awt.Dimension(75, 50));
        AvatarOkButton.setPreferredSize(new java.awt.Dimension(75, 50));
        AvatarOkButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AvatarOkButtonMouseClicked(evt);
            }
        });
        AvatarOkButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AvatarOkButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AvatarChooserPanelLayout = new javax.swing.GroupLayout(AvatarChooserPanel);
        AvatarChooserPanel.setLayout(AvatarChooserPanelLayout);
        AvatarChooserPanelLayout.setHorizontalGroup(
            AvatarChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AvatarChooserPanelLayout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addGroup(AvatarChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(AvatarIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(AvatarChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(AvatarChooserPanelLayout.createSequentialGroup()
                            .addGroup(AvatarChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(MaleWhiteButton)
                                .addComponent(MaleBlackButton))
                            .addGap(50, 50, 50)
                            .addGroup(AvatarChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(FemaleBlackButton)
                                .addComponent(FemaleWhiteButton)))
                        .addComponent(AvatarName, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AvatarNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(AvatarImage, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(100, 100, 100)
                .addComponent(AvatarOkButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        AvatarChooserPanelLayout.setVerticalGroup(
            AvatarChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AvatarChooserPanelLayout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addComponent(AvatarIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addComponent(AvatarNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(AvatarName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(AvatarChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaleWhiteButton)
                    .addComponent(FemaleWhiteButton))
                .addGap(25, 25, 25)
                .addGroup(AvatarChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(MaleBlackButton)
                    .addComponent(FemaleBlackButton))
                .addGroup(AvatarChooserPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(AvatarChooserPanelLayout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addComponent(AvatarImage, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(68, 68, 68))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AvatarChooserPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(AvatarOkButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25))))
        );

        FemaleWhiteButton.getAccessibleContext().setAccessibleName("FemaleButton");

        javax.swing.GroupLayout AvatarChooserDialogLayout = new javax.swing.GroupLayout(AvatarChooserDialog.getContentPane());
        AvatarChooserDialog.getContentPane().setLayout(AvatarChooserDialogLayout);
        AvatarChooserDialogLayout.setHorizontalGroup(
            AvatarChooserDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AvatarChooserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        AvatarChooserDialogLayout.setVerticalGroup(
            AvatarChooserDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AvatarChooserPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        KeyboardSettingsDialog.setMinimumSize(new java.awt.Dimension(600, 750));
        KeyboardSettingsDialog.setResizable(false);
        KeyboardSettingsDialog.addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                KeyboardSettingsDialogWindowOpened(evt);
            }
        });

        KeyBoardPanel.setBackground(new java.awt.Color(93, 150, 199));
        KeyBoardPanel.setMaximumSize(new java.awt.Dimension(600, 750));
        KeyBoardPanel.setMinimumSize(new java.awt.Dimension(600, 750));
        KeyBoardPanel.setPreferredSize(new java.awt.Dimension(600, 750));

        KeyboardSettingsLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/KEYBOARDSETTINGS.png"))); // NOI18N

        MoveUpLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MOVEUP.png"))); // NOI18N

        MoveDownLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MOVEDOWN.png"))); // NOI18N

        MoveLeftLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MOVELEFT.png"))); // NOI18N

        MoveRightLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MOVERIGHT.png"))); // NOI18N

        InteractLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/INTERACT.png"))); // NOI18N

        PauseLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/PAUSE.png"))); // NOI18N

        MapLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MAP.png"))); // NOI18N

        OpenInventoryField.setEditable(false);
        OpenInventoryField.setBackground(new java.awt.Color(0, 153, 204));
        OpenInventoryField.setFont(new java.awt.Font("Arial", 1, 13)); // NOI18N
        OpenInventoryField.setForeground(new java.awt.Color(204, 255, 255));
        OpenInventoryField.setText(" Tab");
        OpenInventoryField.setAutoscrolls(false);
        OpenInventoryField.setMaximumSize(new java.awt.Dimension(100, 50));
        OpenInventoryField.setMinimumSize(new java.awt.Dimension(100, 50));
        OpenInventoryField.setPreferredSize(new java.awt.Dimension(100, 50));
        OpenInventoryField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                OpenInventoryFieldActionPerformed(evt);
            }
        });

        OpenInventoryLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/INVENTORY.png"))); // NOI18N

        KeyboardSettingsCloseButton.setBackground(new java.awt.Color(93, 150, 199));
        KeyboardSettingsCloseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/exitbutton24.png"))); // NOI18N
        KeyboardSettingsCloseButton.setBorder(null);
        KeyboardSettingsCloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KeyboardSettingsCloseButtonActionPerformed(evt);
            }
        });

        MoveUpField.setBackground(new java.awt.Color(0, 153, 204));
        MoveUpField.setForeground(new java.awt.Color(204, 255, 255));
        MoveUpField.setMaximumSize(new java.awt.Dimension(100, 50));
        MoveUpField.setMinimumSize(new java.awt.Dimension(100, 50));
        MoveUpField.setPreferredSize(new java.awt.Dimension(100, 50));
        MoveUpField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MoveUpFieldMouseClicked(evt);
            }
        });
        MoveUpField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MoveUpFieldKeyPressed(evt);
            }
        });

        MoveDownField.setBackground(new java.awt.Color(0, 153, 204));
        MoveDownField.setForeground(new java.awt.Color(204, 255, 255));
        MoveDownField.setMaximumSize(new java.awt.Dimension(100, 50));
        MoveDownField.setMinimumSize(new java.awt.Dimension(100, 50));
        MoveDownField.setPreferredSize(new java.awt.Dimension(100, 50));
        MoveDownField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MoveDownFieldMouseClicked(evt);
            }
        });
        MoveDownField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MoveDownFieldKeyPressed(evt);
            }
        });

        MoveLeftField.setBackground(new java.awt.Color(0, 153, 204));
        MoveLeftField.setForeground(new java.awt.Color(204, 255, 255));
        MoveLeftField.setMaximumSize(new java.awt.Dimension(100, 50));
        MoveLeftField.setMinimumSize(new java.awt.Dimension(100, 50));
        MoveLeftField.setPreferredSize(new java.awt.Dimension(100, 50));
        MoveLeftField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MoveLeftFieldMouseClicked(evt);
            }
        });
        MoveLeftField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MoveLeftFieldKeyPressed(evt);
            }
        });

        MoveRightField.setBackground(new java.awt.Color(0, 153, 204));
        MoveRightField.setForeground(new java.awt.Color(204, 255, 255));
        MoveRightField.setMaximumSize(new java.awt.Dimension(100, 50));
        MoveRightField.setMinimumSize(new java.awt.Dimension(100, 50));
        MoveRightField.setPreferredSize(new java.awt.Dimension(100, 50));
        MoveRightField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MoveRightFieldMouseClicked(evt);
            }
        });
        MoveRightField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MoveRightFieldKeyPressed(evt);
            }
        });

        InteractField.setBackground(new java.awt.Color(0, 153, 204));
        InteractField.setForeground(new java.awt.Color(204, 255, 255));
        InteractField.setMaximumSize(new java.awt.Dimension(100, 50));
        InteractField.setMinimumSize(new java.awt.Dimension(100, 50));
        InteractField.setPreferredSize(new java.awt.Dimension(100, 50));
        InteractField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                InteractFieldMouseClicked(evt);
            }
        });
        InteractField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                InteractFieldKeyPressed(evt);
            }
        });

        PauseField.setBackground(new java.awt.Color(0, 153, 204));
        PauseField.setForeground(new java.awt.Color(204, 255, 255));
        PauseField.setMaximumSize(new java.awt.Dimension(100, 50));
        PauseField.setMinimumSize(new java.awt.Dimension(100, 50));
        PauseField.setPreferredSize(new java.awt.Dimension(100, 50));
        PauseField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PauseFieldMouseClicked(evt);
            }
        });
        PauseField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                PauseFieldKeyPressed(evt);
            }
        });

        MapField.setBackground(new java.awt.Color(0, 153, 204));
        MapField.setForeground(new java.awt.Color(204, 255, 255));
        MapField.setMaximumSize(new java.awt.Dimension(100, 50));
        MapField.setMinimumSize(new java.awt.Dimension(100, 50));
        MapField.setPreferredSize(new java.awt.Dimension(100, 50));
        MapField.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                MapFieldMouseClicked(evt);
            }
        });
        MapField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                MapFieldKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout KeyBoardPanelLayout = new javax.swing.GroupLayout(KeyBoardPanel);
        KeyBoardPanel.setLayout(KeyBoardPanelLayout);
        KeyBoardPanelLayout.setHorizontalGroup(
            KeyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, KeyBoardPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(KeyboardSettingsCloseButton))
            .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                .addGroup(KeyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                        .addGap(134, 134, 134)
                        .addComponent(KeyboardSettingsLabel))
                    .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addGroup(KeyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                                .addComponent(MoveDownLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(MoveDownField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                                .addComponent(MoveLeftLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(MoveLeftField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                                .addComponent(MoveRightLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(MoveRightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                                .addComponent(OpenInventoryLabel)
                                .addGap(100, 100, 100)
                                .addComponent(OpenInventoryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                                .addComponent(MoveUpLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(MoveUpField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                                .addGroup(KeyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(PauseLabel)
                                    .addComponent(InteractLabel)
                                    .addComponent(MapLabel))
                                .addGap(100, 100, 100)
                                .addGroup(KeyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(MapField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(InteractField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(PauseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(134, Short.MAX_VALUE))
        );
        KeyBoardPanelLayout.setVerticalGroup(
            KeyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                .addComponent(KeyboardSettingsCloseButton)
                .addGap(18, 18, 18)
                .addGroup(KeyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                        .addGroup(KeyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                                .addGroup(KeyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                                        .addGroup(KeyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                                                .addGroup(KeyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                                                        .addGroup(KeyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                            .addGroup(KeyBoardPanelLayout.createSequentialGroup()
                                                                .addComponent(KeyboardSettingsLabel)
                                                                .addGap(18, 18, 18)
                                                                .addGroup(KeyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                    .addComponent(MoveUpField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                    .addComponent(MoveUpLabel))
                                                                .addGap(25, 25, 25)
                                                                .addComponent(MoveDownLabel))
                                                            .addComponent(MoveDownField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGap(25, 25, 25)
                                                        .addComponent(MoveLeftLabel))
                                                    .addComponent(MoveLeftField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(25, 25, 25)
                                                .addComponent(MoveRightLabel))
                                            .addComponent(MoveRightField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(25, 25, 25)
                                        .addComponent(InteractLabel))
                                    .addComponent(InteractField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(25, 25, 25)
                                .addComponent(PauseLabel))
                            .addComponent(PauseField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(25, 25, 25)
                        .addComponent(MapLabel))
                    .addComponent(MapField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(KeyBoardPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(OpenInventoryField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(OpenInventoryLabel))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        try{
            MaskFormatter mask= new MaskFormatter("*");
            mask.install(MoveUpField);
        } catch(ParseException e){
            e.printStackTrace();
        }
        try{
            MaskFormatter mask= new MaskFormatter("*");
            mask.install(MoveUpField);
        } catch(ParseException e){
            e.printStackTrace();
        }
        try{
            MaskFormatter mask= new MaskFormatter("*");
            mask.install(MoveUpField);
        } catch(ParseException e){
            e.printStackTrace();
        }
        try{
            MaskFormatter mask= new MaskFormatter("*");
            mask.install(MoveUpField);
        } catch(ParseException e){
            e.printStackTrace();
        }
        try{
            MaskFormatter mask= new MaskFormatter("*");
            mask.install(MoveUpField);
        } catch(ParseException e){
            e.printStackTrace();
        }
        try{
            MaskFormatter mask= new MaskFormatter("*");
            mask.install(MoveUpField);
        } catch(ParseException e){
            e.printStackTrace();
        }
        try{
            MaskFormatter mask= new MaskFormatter("*");
            mask.install(MoveUpField);
        } catch(ParseException e){
            e.printStackTrace();
        }

        javax.swing.GroupLayout KeyboardSettingsDialogLayout = new javax.swing.GroupLayout(KeyboardSettingsDialog.getContentPane());
        KeyboardSettingsDialog.getContentPane().setLayout(KeyboardSettingsDialogLayout);
        KeyboardSettingsDialogLayout.setHorizontalGroup(
            KeyboardSettingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KeyboardSettingsDialogLayout.createSequentialGroup()
                .addComponent(KeyBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        KeyboardSettingsDialogLayout.setVerticalGroup(
            KeyboardSettingsDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(KeyboardSettingsDialogLayout.createSequentialGroup()
                .addComponent(KeyBoardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        ConvDialog.setBackground(new java.awt.Color(0, 0, 0));
        ConvDialog.setMinimumSize(new java.awt.Dimension(375, 100));
        ConvDialog.setResizable(false);
        ConvDialog.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                ConvDialogWindowLostFocus(evt);
            }
        });
        ConvDialog.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                ConvDialogKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ConvDialogKeyTyped(evt);
            }
        });

        ConversationScrollPane.setBorder(null);
        ConversationScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        ConversationScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        ConversationScrollPane.setMaximumSize(new java.awt.Dimension(375, 100));
        ConversationScrollPane.setMinimumSize(new java.awt.Dimension(375, 100));
        ConversationScrollPane.setName(""); // NOI18N
        ConversationScrollPane.setPreferredSize(new java.awt.Dimension(375, 100));

        ConversationTextArea.setEditable(false);
        ConversationTextArea.setBackground(new java.awt.Color(255, 255, 204));
        ConversationTextArea.setColumns(20);
        ConversationTextArea.setFont(new java.awt.Font("Arial", 0, 17)); // NOI18N
        ConversationTextArea.setLineWrap(true);
        ConversationTextArea.setRows(5);
        ConversationTextArea.setWrapStyleWord(true);
        ConversationTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 13))); // NOI18N
        ConversationTextArea.setMaximumSize(new java.awt.Dimension(375, 100));
        ConversationTextArea.setMinimumSize(new java.awt.Dimension(375, 100));
        ConversationTextArea.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                ConversationTextAreaKeyTyped(evt);
            }
        });
        ConversationScrollPane.setViewportView(ConversationTextArea);

        javax.swing.GroupLayout ConvDialogLayout = new javax.swing.GroupLayout(ConvDialog.getContentPane());
        ConvDialog.getContentPane().setLayout(ConvDialogLayout);
        ConvDialogLayout.setHorizontalGroup(
            ConvDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ConversationScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ConvDialogLayout.setVerticalGroup(
            ConvDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ConversationScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        HintDialog.setMinimumSize(new java.awt.Dimension(200, 50));
        HintDialog.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
                HintDialogWindowGainedFocus(evt);
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                HintDialogWindowLostFocus(evt);
            }
        });
        HintDialog.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                HintDialogKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                HintDialogKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                HintDialogKeyTyped(evt);
            }
        });

        HintScrollPane.setBorder(null);
        HintScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        HintScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        HintScrollPane.setMaximumSize(new java.awt.Dimension(200, 50));
        HintScrollPane.setMinimumSize(new java.awt.Dimension(200, 50));
        HintScrollPane.setName(""); // NOI18N
        HintScrollPane.setPreferredSize(new java.awt.Dimension(200, 50));

        HintTextArea.setEditable(false);
        HintTextArea.setBackground(new java.awt.Color(255, 255, 204));
        HintTextArea.setColumns(20);
        HintTextArea.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        HintTextArea.setLineWrap(true);
        HintTextArea.setRows(5);
        HintTextArea.setWrapStyleWord(true);
        HintTextArea.setMaximumSize(new java.awt.Dimension(200, 50));
        HintTextArea.setMinimumSize(new java.awt.Dimension(200, 50));
        HintTextArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                HintTextAreaMouseClicked(evt);
            }
        });
        HintScrollPane.setViewportView(HintTextArea);

        javax.swing.GroupLayout HintDialogLayout = new javax.swing.GroupLayout(HintDialog.getContentPane());
        HintDialog.getContentPane().setLayout(HintDialogLayout);
        HintDialogLayout.setHorizontalGroup(
            HintDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HintDialogLayout.createSequentialGroup()
                .addComponent(HintScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        HintDialogLayout.setVerticalGroup(
            HintDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HintDialogLayout.createSequentialGroup()
                .addComponent(HintScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        QuestDialog.setMinimumSize(new java.awt.Dimension(420, 400));
        QuestDialog.setResizable(false);
        QuestDialog.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                QuestDialogWindowLostFocus(evt);
            }
        });

        QuestPanel.setBackground(new java.awt.Color(93, 150, 199));
        QuestPanel.setMaximumSize(new java.awt.Dimension(420, 400));
        QuestPanel.setMinimumSize(new java.awt.Dimension(420, 400));
        QuestPanel.setName(""); // NOI18N
        QuestPanel.setPreferredSize(new java.awt.Dimension(420, 400));

        QuestListScrollPane.setToolTipText("");
        QuestListScrollPane.setMaximumSize(new java.awt.Dimension(190, 350));
        QuestListScrollPane.setMinimumSize(new java.awt.Dimension(190, 350));
        QuestListScrollPane.setPreferredSize(new java.awt.Dimension(190, 350));

        QuestList.setBorder(javax.swing.BorderFactory.createTitledBorder("Quest"));
        QuestList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Collect your first dollar!", "It's time to get Math Exam!" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        QuestList.setMaximumSize(new java.awt.Dimension(190, 350));
        QuestList.setMinimumSize(new java.awt.Dimension(190, 350));
        QuestList.setPreferredSize(new java.awt.Dimension(190, 350));
        QuestList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                QuestListValueChanged(evt);
            }
        });
        QuestListScrollPane.setViewportView(QuestList);

        QuestTextScrollPane.setMaximumSize(new java.awt.Dimension(190, 350));
        QuestTextScrollPane.setMinimumSize(new java.awt.Dimension(190, 350));
        QuestTextScrollPane.setPreferredSize(new java.awt.Dimension(190, 350));

        QuestTextArea.setEditable(false);
        QuestTextArea.setColumns(20);
        QuestTextArea.setLineWrap(true);
        QuestTextArea.setRows(5);
        QuestTextArea.setBorder(javax.swing.BorderFactory.createTitledBorder("Description"));
        QuestTextArea.setMaximumSize(new java.awt.Dimension(190, 350));
        QuestTextArea.setMinimumSize(new java.awt.Dimension(190, 350));
        QuestTextArea.setPreferredSize(new java.awt.Dimension(190, 350));
        QuestTextScrollPane.setViewportView(QuestTextArea);

        ExitQuestDialogLabel.setForeground(new java.awt.Color(204, 255, 255));
        ExitQuestDialogLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ExitQuestDialogLabel.setText("X");
        ExitQuestDialogLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitQuestDialogLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout QuestPanelLayout = new javax.swing.GroupLayout(QuestPanel);
        QuestPanel.setLayout(QuestPanelLayout);
        QuestPanelLayout.setHorizontalGroup(
            QuestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuestPanelLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(QuestListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(QuestTextScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, QuestPanelLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(ExitQuestDialogLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        QuestPanelLayout.setVerticalGroup(
            QuestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(QuestPanelLayout.createSequentialGroup()
                .addComponent(ExitQuestDialogLabel)
                .addGap(9, 9, 9)
                .addGroup(QuestPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(QuestTextScrollPane, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(QuestListScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout QuestDialogLayout = new javax.swing.GroupLayout(QuestDialog.getContentPane());
        QuestDialog.getContentPane().setLayout(QuestDialogLayout);
        QuestDialogLayout.setHorizontalGroup(
            QuestDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(QuestPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        QuestDialogLayout.setVerticalGroup(
            QuestDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(QuestPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        InventoryDialog.setMinimumSize(new java.awt.Dimension(500, 500));
        InventoryDialog.setResizable(false);
        InventoryDialog.addWindowFocusListener(new java.awt.event.WindowFocusListener() {
            public void windowGainedFocus(java.awt.event.WindowEvent evt) {
            }
            public void windowLostFocus(java.awt.event.WindowEvent evt) {
                InventoryDialogWindowLostFocus(evt);
            }
        });

        InventoryPanel.setBackground(new java.awt.Color(93, 150, 199));
        InventoryPanel.setMaximumSize(new java.awt.Dimension(500, 500));
        InventoryPanel.setMinimumSize(new java.awt.Dimension(500, 500));
        InventoryPanel.setPreferredSize(new java.awt.Dimension(500, 500));

        InventoryLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/INVENTORYDIALOG.png"))); // NOI18N

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        InventoryTable.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        InventoryTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item", "Icon"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        InventoryTable.setColumnSelectionAllowed(true);
        InventoryTable.setMaximumSize(new java.awt.Dimension(450, 450));
        InventoryTable.setMinimumSize(new java.awt.Dimension(450, 450));
        InventoryTable.setPreferredSize(new java.awt.Dimension(450, 450));
        jScrollPane1.setViewportView(InventoryTable);
        InventoryTable.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        ExitInventoryDialog.setForeground(new java.awt.Color(204, 255, 255));
        ExitInventoryDialog.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ExitInventoryDialog.setText("X");
        ExitInventoryDialog.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ExitInventoryDialogMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout InventoryPanelLayout = new javax.swing.GroupLayout(InventoryPanel);
        InventoryPanel.setLayout(InventoryPanelLayout);
        InventoryPanelLayout.setHorizontalGroup(
            InventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryPanelLayout.createSequentialGroup()
                .addGroup(InventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(InventoryPanelLayout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(InventoryPanelLayout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(InventoryLabel)))
                .addGap(40, 40, 40))
            .addComponent(ExitInventoryDialog, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        InventoryPanelLayout.setVerticalGroup(
            InventoryPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(InventoryPanelLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(ExitInventoryDialog)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(InventoryLabel)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 412, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout InventoryDialogLayout = new javax.swing.GroupLayout(InventoryDialog.getContentPane());
        InventoryDialog.getContentPane().setLayout(InventoryDialogLayout);
        InventoryDialogLayout.setHorizontalGroup(
            InventoryDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InventoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        InventoryDialogLayout.setVerticalGroup(
            InventoryDialogLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(InventoryPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("HNTBAE\n"); // NOI18N
        setBackground(new java.awt.Color(93, 150, 199));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setForeground(java.awt.Color.blue);
        setMinimumSize(new java.awt.Dimension(600, 750));
        setName("PrincipalJFrame\n"); // NOI18N
        setUndecorated(true);
        setResizable(false);
        setSize(new java.awt.Dimension(600, 750));
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
        });

        HudPanel.setBackground(new java.awt.Color(93, 150, 199));
        HudPanel.setMaximumSize(new java.awt.Dimension(600, 175));
        HudPanel.setMinimumSize(new java.awt.Dimension(600, 175));
        HudPanel.setPreferredSize(new java.awt.Dimension(600, 175));

        LevelLabel.setBackground(new java.awt.Color(93, 150, 199));
        LevelLabel.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        LevelLabel.setForeground(new java.awt.Color(255, 255, 255));
        LevelLabel.setText("LEVEL 1");
        LevelLabel.setMaximumSize(new java.awt.Dimension(225, 25));
        LevelLabel.setMinimumSize(new java.awt.Dimension(225, 25));
        LevelLabel.setPreferredSize(new java.awt.Dimension(225, 25));

        MoneyIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/moneta.png"))); // NOI18N
        MoneyIcon.setText("jLabel2");
        MoneyIcon.setMaximumSize(new java.awt.Dimension(25, 25));
        MoneyIcon.setMinimumSize(new java.awt.Dimension(25, 25));
        MoneyIcon.setPreferredSize(new java.awt.Dimension(25, 25));

        MoneyLabel.setFont(new java.awt.Font("Arial Black", 0, 18)); // NOI18N
        MoneyLabel.setForeground(new java.awt.Color(255, 255, 51));
        MoneyLabel.setText("0");
        MoneyLabel.setMaximumSize(new java.awt.Dimension(125, 25));
        MoneyLabel.setMinimumSize(new java.awt.Dimension(125, 25));
        MoneyLabel.setPreferredSize(new java.awt.Dimension(125, 25));

        EnergyProgressBar.setBackground(new java.awt.Color(204, 255, 255));
        EnergyProgressBar.setForeground(new java.awt.Color(108, 212, 255));
        EnergyProgressBar.setMaximumSize(new java.awt.Dimension(150, 25));
        EnergyProgressBar.setMinimumSize(new java.awt.Dimension(150, 25));
        EnergyProgressBar.setPreferredSize(new java.awt.Dimension(150, 25));

        EnergyIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/ENERGY25.png"))); // NOI18N
        EnergyIcon.setText("jLabel2");
        EnergyIcon.setMaximumSize(new java.awt.Dimension(25, 25));
        EnergyIcon.setMinimumSize(new java.awt.Dimension(25, 25));
        EnergyIcon.setPreferredSize(new java.awt.Dimension(25, 25));

        StressIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/STRESS25.png"))); // NOI18N
        StressIcon.setText("jLabel2");
        StressIcon.setMaximumSize(new java.awt.Dimension(25, 25));
        StressIcon.setMinimumSize(new java.awt.Dimension(25, 25));
        StressIcon.setPreferredSize(new java.awt.Dimension(25, 25));

        HungerIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/HUNGERICON25.png"))); // NOI18N
        HungerIcon.setText("jLabel2");
        HungerIcon.setMaximumSize(new java.awt.Dimension(25, 25));
        HungerIcon.setMinimumSize(new java.awt.Dimension(25, 25));
        HungerIcon.setPreferredSize(new java.awt.Dimension(25, 25));

        StressProgressBar.setBackground(new java.awt.Color(255, 255, 204));
        StressProgressBar.setForeground(new java.awt.Color(254, 215, 102));
        StressProgressBar.setMaximumSize(new java.awt.Dimension(150, 25));
        StressProgressBar.setMinimumSize(new java.awt.Dimension(150, 25));
        StressProgressBar.setPreferredSize(new java.awt.Dimension(150, 25));

        HungerProgressBar.setBackground(new java.awt.Color(255, 204, 204));
        HungerProgressBar.setForeground(new java.awt.Color(195, 60, 84));
        HungerProgressBar.setMaximumSize(new java.awt.Dimension(150, 25));
        HungerProgressBar.setMinimumSize(new java.awt.Dimension(150, 25));
        HungerProgressBar.setPreferredSize(new java.awt.Dimension(150, 25));

        SettingsButtonFrame.setBackground(new java.awt.Color(93, 150, 199));
        SettingsButtonFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/settings25.png"))); // NOI18N
        SettingsButtonFrame.setBorder(null);
        SettingsButtonFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SettingsButtonFrameActionPerformed(evt);
            }
        });

        QuestButtonFrame.setBackground(new java.awt.Color(93, 150, 199));
        QuestButtonFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/questbutton24.png"))); // NOI18N
        QuestButtonFrame.setBorder(null);
        QuestButtonFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QuestButtonFrameActionPerformed(evt);
            }
        });

        InventoryButtonFrame.setBackground(new java.awt.Color(93, 150, 199));
        InventoryButtonFrame.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/backpack.png"))); // NOI18N
        InventoryButtonFrame.setToolTipText("inventory");
        InventoryButtonFrame.setBorder(null);
        InventoryButtonFrame.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                InventoryButtonFrameActionPerformed(evt);
            }
        });

        CareerButtonFrame1.setBackground(new java.awt.Color(93, 150, 199));
        CareerButtonFrame1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/libretto.png"))); // NOI18N
        CareerButtonFrame1.setToolTipText("inventory");
        CareerButtonFrame1.setBorder(null);
        CareerButtonFrame1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CareerButtonFrame1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout HudPanelLayout = new javax.swing.GroupLayout(HudPanel);
        HudPanel.setLayout(HudPanelLayout);
        HudPanelLayout.setHorizontalGroup(
            HudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HudPanelLayout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(HudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HudPanelLayout.createSequentialGroup()
                        .addComponent(LevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75)
                        .addComponent(EnergyProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(25, 25, 25)
                        .addComponent(EnergyIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(HudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(HudPanelLayout.createSequentialGroup()
                            .addComponent(SettingsButtonFrame)
                            .addGap(18, 18, 18)
                            .addComponent(QuestButtonFrame)
                            .addGap(18, 18, 18)
                            .addComponent(InventoryButtonFrame)
                            .addGap(18, 18, 18)
                            .addComponent(CareerButtonFrame1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(HungerProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(26, 26, 26)
                            .addComponent(HungerIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(HudPanelLayout.createSequentialGroup()
                            .addComponent(MoneyIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10)
                            .addComponent(MoneyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(139, 139, 139)
                            .addComponent(StressProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(25, 25, 25)
                            .addComponent(StressIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(50, 50, 50))
        );
        HudPanelLayout.setVerticalGroup(
            HudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(HudPanelLayout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(HudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(LevelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnergyProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(EnergyIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(HudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(HudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(MoneyLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(MoneyIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(StressProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(StressIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(HudPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(HungerProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(HungerIcon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SettingsButtonFrame)
                    .addComponent(QuestButtonFrame)
                    .addComponent(InventoryButtonFrame)
                    .addComponent(CareerButtonFrame1))
                .addGap(29, 29, 29))
        );

        RightBorder.setBackground(new java.awt.Color(93, 150, 199));
        RightBorder.setPreferredSize(new java.awt.Dimension(50, 100));

        GameCloseButton.setBackground(new java.awt.Color(93, 150, 199));
        GameCloseButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/exitbutton24.png"))); // NOI18N
        GameCloseButton.setBorder(null);
        GameCloseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GameCloseButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout RightBorderLayout = new javax.swing.GroupLayout(RightBorder);
        RightBorder.setLayout(RightBorderLayout);
        RightBorderLayout.setHorizontalGroup(
            RightBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, RightBorderLayout.createSequentialGroup()
                .addGap(0, 25, Short.MAX_VALUE)
                .addComponent(GameCloseButton))
        );
        RightBorderLayout.setVerticalGroup(
            RightBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(RightBorderLayout.createSequentialGroup()
                .addComponent(GameCloseButton)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        LeftBorder.setBackground(new java.awt.Color(93, 150, 199));
        LeftBorder.setPreferredSize(new java.awt.Dimension(50, 100));

        javax.swing.GroupLayout LeftBorderLayout = new javax.swing.GroupLayout(LeftBorder);
        LeftBorder.setLayout(LeftBorderLayout);
        LeftBorderLayout.setHorizontalGroup(
            LeftBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 50, Short.MAX_VALUE)
        );
        LeftBorderLayout.setVerticalGroup(
            LeftBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        UpperBorder.setBackground(new java.awt.Color(93, 150, 199));
        UpperBorder.setMaximumSize(new java.awt.Dimension(500, 75));
        UpperBorder.setMinimumSize(new java.awt.Dimension(500, 75));
        UpperBorder.setPreferredSize(new java.awt.Dimension(500, 75));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/Title.png"))); // NOI18N
        jLabel2.setMaximumSize(new java.awt.Dimension(210, 70));
        jLabel2.setMinimumSize(new java.awt.Dimension(210, 70));
        jLabel2.setPreferredSize(new java.awt.Dimension(210, 70));
        jLabel2.setRequestFocusEnabled(false);
        jLabel2.setVerifyInputWhenFocusTarget(false);

        javax.swing.GroupLayout UpperBorderLayout = new javax.swing.GroupLayout(UpperBorder);
        UpperBorder.setLayout(UpperBorderLayout);
        UpperBorderLayout.setHorizontalGroup(
            UpperBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpperBorderLayout.createSequentialGroup()
                .addGap(145, 145, 145)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(145, 145, 145))
        );
        UpperBorderLayout.setVerticalGroup(
            UpperBorderLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(UpperBorderLayout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 5, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(HudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(LeftBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(UpperBorder, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(RightBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(RightBorder, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                    .addComponent(LeftBorder, javax.swing.GroupLayout.DEFAULT_SIZE, 575, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(UpperBorder, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 500, Short.MAX_VALUE)))
                .addComponent(HudPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        setSize(new java.awt.Dimension(600, 750));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void ConfirmAnswerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConfirmAnswerActionPerformed
        ExamManager.getInstance().setConfirm();
    }//GEN-LAST:event_ConfirmAnswerActionPerformed

    private void AudioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AudioButtonActionPerformed
        // DA CANCELLARE, CREATO COL COMMIT DI PEPPE
    }//GEN-LAST:event_AudioButtonActionPerformed

    private void NewGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NewGameButtonActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        GameManager.getInstance().initGame();

        SwingUtilities.invokeLater(() -> AvatarChooserDialog.setVisible(true));
        SwingUtilities.invokeLater(() -> MainMenuDialog.setVisible(false));
        SwingUtilities.invokeLater(() -> AvatarName.setText(EMPTY_TEXT));
        SwingUtilities.invokeLater(() -> AvatarOkButton.setEnabled(false));
        name = "";
        avatar = 0;
    }//GEN-LAST:event_NewGameButtonActionPerformed

    private void SettingsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsButtonActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        SwingUtilities.invokeLater(() -> SettingsDialog.setVisible(true));
        //SwingUtilities.invokeLater(() -> MainMenuDialog.setVisible(false));
    }//GEN-LAST:event_SettingsButtonActionPerformed

    private void ResumeGameButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResumeGameButtonActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        SwingUtilities.invokeLater(() -> music.play("game_music"));
        SwingUtilities.invokeLater(() -> {
                GameManager.getInstance().initGame();
                GameManager.getInstance().loadGame();
                SwingUtilities.invokeLater(() -> this.setVisible(true));
        });
    }//GEN-LAST:event_ResumeGameButtonActionPerformed

    private void ReturnToMainMenuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReturnToMainMenuButtonActionPerformed
        // QUI EVENTUALE CHIAMATA A FUNZIONE SE SIAMO IN GIOCO PER AVVISARE DI SALVARE ETC...
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        SwingUtilities.invokeLater(() -> MainMenuDialog.setVisible(true));
        SwingUtilities.invokeLater(() -> SettingsDialog.setVisible(false));
        SwingUtilities.invokeLater(() -> GameManager.getInstance().stopGame());
    }//GEN-LAST:event_ReturnToMainMenuButtonActionPerformed

    private void YesButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_YesButtonActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("yes"));
        SwingUtilities.invokeLater(() -> RequestManager.setRESULT(true));
        SwingUtilities.invokeLater(() -> RequestDialog.setVisible(false));
        SwingUtilities.invokeLater(() -> RequestDialog.setFocusable(false));
    }//GEN-LAST:event_YesButtonActionPerformed

    private void FemaleWhiteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FemaleWhiteButtonActionPerformed
        SwingUtilities.invokeLater(() -> settingAvatarButton());
        SwingUtilities.invokeLater(() -> FemaleWhiteButton.setSelected(true));
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        SwingUtilities.invokeLater(() -> StudentLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/woman75.png"))));
        SwingUtilities.invokeLater(() -> AvatarOkButton.setEnabled(true));
        SwingUtilities.invokeLater(() -> avatar = 1);
    }//GEN-LAST:event_FemaleWhiteButtonActionPerformed

    private void ExitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExitButtonActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        SwingUtilities.invokeLater(() -> SaveManager.getSaveManager().save());
        SwingUtilities.invokeLater(() -> System.exit(0));
    }//GEN-LAST:event_ExitButtonActionPerformed

    private void KeyboardButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeyboardButtonActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        SwingUtilities.invokeLater(() -> KeyboardSettingsDialog.setVisible(true));
        /*SwingUtilities.invokeLater(() -> ConvDialog.setVisible(true));
        SwingUtilities.invokeLater(()-> SettingsDialog.setVisible(false));*/
    }//GEN-LAST:event_KeyboardButtonActionPerformed

    private void AvatarOkButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AvatarOkButtonActionPerformed
        name = AvatarName.getText();
        if (!name.equals("")) {
            SwingUtilities.invokeLater(() -> AvatarChooserDialog.setVisible(false));
            SwingUtilities.invokeLater(() -> this.setVisible(true));
            SwingUtilities.invokeLater(() -> this.setEnabled(true));
            GameManager.getInstance().startGame(avatar, name);
            SwingUtilities.invokeLater(() -> music.play("game_music"));
        } else
            return;
    }//GEN-LAST:event_AvatarOkButtonActionPerformed

    private void LanguageComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LanguageComboBoxActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("menu"));
    }//GEN-LAST:event_LanguageComboBoxActionPerformed

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ESCAPE) {
            SwingUtilities.invokeLater(() -> SettingsDialog.setVisible(true));
        }

    }//GEN-LAST:event_formKeyPressed

    private void MainMenuDialogComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_MainMenuDialogComponentShown

        if (MainMenuDialog.isVisible()) {
            //SwingUtilities.invokeLater(() ->this.validate());
            //SwingUtilities.invokeLater(() -> this.invalidate());
            SwingUtilities.invokeLater(() -> this.setVisible(false));

        }
    }//GEN-LAST:event_MainMenuDialogComponentShown

    private void FirstAnswerMouseClicked(java.awt.event.MouseEvent evt) {
        ExamManager.getInstance().setRESULT(1);
        SwingUtilities.invokeLater(() -> ConfirmAnswer.setEnabled(true));

    }

    private void SecondAnswerMouseClicked(java.awt.event.MouseEvent evt) {
        ExamManager.getInstance().setRESULT(2);
        SwingUtilities.invokeLater(() -> ConfirmAnswer.setEnabled(true));
    }

    private void ThirdAnswerMouseClicked(java.awt.event.MouseEvent evt) {
        ExamManager.getInstance().setRESULT(3);
        SwingUtilities.invokeLater(() -> ConfirmAnswer.setEnabled(true));
    }

    private void FourthAnswerMouseClicked(java.awt.event.MouseEvent evt) {
        ExamManager.getInstance().setRESULT(4);
        SwingUtilities.invokeLater(() -> ConfirmAnswer.setEnabled(true));
    }

    private void AvatarOkButtonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AvatarOkButtonMouseClicked

    }//GEN-LAST:event_AvatarOkButtonMouseClicked

    private void HintDialogKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HintDialogKeyTyped
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            SwingUtilities.invokeLater(() -> HintDialog.setVisible(false));
            SwingUtilities.invokeLater(() -> HintTextArea.setText(EMPTY_TEXT));
            SwingUtilities.invokeLater(() -> HintDialog.setFocusable(false));
        }

    }//GEN-LAST:event_HintDialogKeyTyped

    private void ConvDialogKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ConvDialogKeyTyped
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            SwingUtilities.invokeLater(() -> ConvDialog.setVisible(false));
            SwingUtilities.invokeLater(() -> ConversationTextArea.setText(EMPTY_TEXT));
            SwingUtilities.invokeLater(() -> ConvDialog.setFocusable(false));
        }

    }//GEN-LAST:event_ConvDialogKeyTyped

    private void HintDialogKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HintDialogKeyPressed

    }//GEN-LAST:event_HintDialogKeyPressed

    private void NoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NoButtonActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("no"));
        SwingUtilities.invokeLater(() -> RequestManager.setRESULT(false));
        SwingUtilities.invokeLater(() -> RequestDialog.setVisible(false));
        SwingUtilities.invokeLater(() -> RequestDialog.setFocusable(false));
    }//GEN-LAST:event_NoButtonActionPerformed

    private void QuestListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_QuestListValueChanged
        if (QuestList.getSelectedIndex() == 0)
            SwingUtilities.invokeLater(() -> QuestTextArea.setText("Collect your first dollar!\nKeep your eyes open,\nyou'll find others around\n the campus."));
        else if (QuestList.getSelectedIndex() == 1)
            SwingUtilities.invokeLater(() -> QuestTextArea.setText("Ouch! It's time to get\nAnalisi 1 Exam!\nGo find a calculator."));
    }//GEN-LAST:event_QuestListValueChanged

    private void SettingsButtonFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsButtonFrameActionPerformed
        SwingUtilities.invokeLater(() -> SettingsDialog.setVisible(true));

        //Chiamata a simone che mette il gioco in not game state
    }//GEN-LAST:event_SettingsButtonFrameActionPerformed

    private void GameCloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GameCloseButtonActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        SwingUtilities.invokeLater(() -> SaveManager.getSaveManager().save());
        SwingUtilities.invokeLater(() -> System.exit(0));
    }//GEN-LAST:event_GameCloseButtonActionPerformed

    private void SettingsCloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SettingsCloseButtonActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        SwingUtilities.invokeLater(() -> SettingsDialog.setVisible(false));
    }//GEN-LAST:event_SettingsCloseButtonActionPerformed

    private void QuestButtonFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QuestButtonFrameActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        SwingUtilities.invokeLater(() -> QuestDialog.setVisible(true));
    }//GEN-LAST:event_QuestButtonFrameActionPerformed

    private void MaleWhiteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaleWhiteButtonActionPerformed
        SwingUtilities.invokeLater(() -> settingAvatarButton());
        SwingUtilities.invokeLater(() -> MaleWhiteButton.setSelected(true));
        SwingUtilities.invokeLater(() -> StudentLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/man75.png"))));
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        SwingUtilities.invokeLater(() -> AvatarOkButton.setEnabled(true));
        SwingUtilities.invokeLater(() -> avatar = 0);
    }//GEN-LAST:event_MaleWhiteButtonActionPerformed

    private void FemaleBlackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FemaleBlackButtonActionPerformed
        SwingUtilities.invokeLater(() -> settingAvatarButton());
        SwingUtilities.invokeLater(() -> FemaleBlackButton.setSelected(true));
        SwingUtilities.invokeLater(() -> StudentLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/BLACKwoman75.png"))));
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        SwingUtilities.invokeLater(() -> AvatarOkButton.setEnabled(true));
        SwingUtilities.invokeLater(() -> avatar = 3);

    }//GEN-LAST:event_FemaleBlackButtonActionPerformed

    private void MaleBlackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MaleBlackButtonActionPerformed
        SwingUtilities.invokeLater(() -> settingAvatarButton());
        SwingUtilities.invokeLater(() -> MaleBlackButton.setSelected(true));
        SwingUtilities.invokeLater(() -> StudentLabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/BLACKman75.png"))));
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        SwingUtilities.invokeLater(() -> AvatarOkButton.setEnabled(true));
        SwingUtilities.invokeLater(() -> avatar = 2);

    }//GEN-LAST:event_MaleBlackButtonActionPerformed

    private void ExitQuestDialogLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitQuestDialogLabelMouseClicked
        SwingUtilities.invokeLater(() -> QuestDialog.setVisible(false));
    }//GEN-LAST:event_ExitQuestDialogLabelMouseClicked

    private void HintTextAreaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_HintTextAreaMouseClicked
        SwingUtilities.invokeLater(() -> HintDialog.setFocusable(true));
    }//GEN-LAST:event_HintTextAreaMouseClicked

    private void HintDialogWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_HintDialogWindowLostFocus
        SwingUtilities.invokeLater(() -> HintDialog.setVisible(false));
    }//GEN-LAST:event_HintDialogWindowLostFocus

    private void ConvDialogWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ConvDialogWindowLostFocus
        SwingUtilities.invokeLater(() -> ConvDialog.setVisible(false));
    }//GEN-LAST:event_ConvDialogWindowLostFocus

    private void ExamDialogWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ExamDialogWindowOpened
        //SwingUtilities.invokeLater(() -> this.setEnabled(false));
    }//GEN-LAST:event_ExamDialogWindowOpened

    private void QuestDialogWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_QuestDialogWindowLostFocus
        SwingUtilities.invokeLater(() -> QuestDialog.setVisible(false));
    }//GEN-LAST:event_QuestDialogWindowLostFocus

    private void ExamDialogWindowDeactivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_ExamDialogWindowDeactivated

    }//GEN-LAST:event_ExamDialogWindowDeactivated

    private void HintDialogWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_HintDialogWindowGainedFocus
        // TODO add your handling code here:
    }//GEN-LAST:event_HintDialogWindowGainedFocus

    private void HintDialogKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_HintDialogKeyReleased
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_SPACE) {
            SwingUtilities.invokeLater(() -> HintDialog.setVisible(false));
            SwingUtilities.invokeLater(() -> HintTextArea.setText(EMPTY_TEXT));
            SwingUtilities.invokeLater(() -> HintDialog.setFocusable(false));
            //SwingUtilities.invokeLater(() -> this.setEnabled(true));
        }
    }//GEN-LAST:event_HintDialogKeyReleased

    private void MusicButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MusicButtonActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        if (JukeBoxMusic.isActive()) {
            MusicButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/NOMUSIC.png")));
            SwingUtilities.invokeLater(() -> music.setIsActive(false));
        } else {
            MusicButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/MUSIC.png")));
            SwingUtilities.invokeLater(() -> music.setIsActive(true));

            if (GameManager.getInstance().isRunning()) {
                SwingUtilities.invokeLater(() -> music.play("game_music"));
            }
        }
    }//GEN-LAST:event_MusicButtonActionPerformed

    private void SoundButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SoundButtonActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        if (JukeBoxSound.isActive()) {
            SoundButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/NOSOUND.png")));
            SwingUtilities.invokeLater(() -> sound.setIsActive(false));
        } else {
            SoundButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icon/SOUND.png")));
            SwingUtilities.invokeLater(() -> sound.setIsActive(true));
        }
    }//GEN-LAST:event_SoundButtonActionPerformed

    private void CreditsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CreditsButtonActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("menu"));
    }//GEN-LAST:event_CreditsButtonActionPerformed

    private void RequestDialogWindowGainedFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_RequestDialogWindowGainedFocus
        // SwingUtilities.invokeLater(() ->  RequestDialog.set
    }//GEN-LAST:event_RequestDialogWindowGainedFocus

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        SaveManager save = SaveManager.getSaveManager();
        if (save.isSaveSomething()) {
            SwingUtilities.invokeLater(() -> ResumeGameButton.setEnabled(true));
        }
    }//GEN-LAST:event_formWindowOpened

    private void OpenInventoryFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_OpenInventoryFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_OpenInventoryFieldActionPerformed

    private void KeyboardSettingsCloseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KeyboardSettingsCloseButtonActionPerformed
        SwingUtilities.invokeLater(() -> sound.play("menu"));
        SwingUtilities.invokeLater(() -> KeyboardSettingsDialog.setVisible(false));
    }//GEN-LAST:event_KeyboardSettingsCloseButtonActionPerformed

    private void InventoryButtonFrameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_InventoryButtonFrameActionPerformed
        SwingUtilities.invokeLater(() -> InventoryDialog.setVisible(true));
    }//GEN-LAST:event_InventoryButtonFrameActionPerformed

    private void CareerButtonFrame1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CareerButtonFrame1ActionPerformed
        SwingUtilities.invokeLater(() -> CareerDialog.setVisible(true));
    }//GEN-LAST:event_CareerButtonFrame1ActionPerformed

    private void ExitCareerDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitCareerDialogMouseClicked
        SwingUtilities.invokeLater(() -> CareerDialog.setVisible(false));
    }//GEN-LAST:event_ExitCareerDialogMouseClicked

    private void ExitInventoryDialogMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ExitInventoryDialogMouseClicked
        SwingUtilities.invokeLater(() -> InventoryDialog.setVisible(false));
    }//GEN-LAST:event_ExitInventoryDialogMouseClicked

    private void InventoryDialogWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_InventoryDialogWindowLostFocus
        SwingUtilities.invokeLater(() -> InventoryDialog.setVisible(false));
    }//GEN-LAST:event_InventoryDialogWindowLostFocus

    private void CareerDialogWindowLostFocus(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_CareerDialogWindowLostFocus
        SwingUtilities.invokeLater(() -> CareerDialog.setVisible(false));
    }//GEN-LAST:event_CareerDialogWindowLostFocus

    private void CareerDialogWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_CareerDialogWindowOpened
        SwingUtilities.invokeLater(() -> setCareer());
    }//GEN-LAST:event_CareerDialogWindowOpened

    private void KeyboardSettingsDialogWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_KeyboardSettingsDialogWindowOpened
        settings = SettingsManager.getSettingsManager();
        SwingUtilities.invokeLater(() -> setKeyBoard());
    }//GEN-LAST:event_KeyboardSettingsDialogWindowOpened

    private void MoveUpFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MoveUpFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SwingUtilities.invokeLater(() -> {
                boolean b = settings.setMoveUp(moveUp);
            });
            SwingUtilities.invokeLater(() -> setKeyBoard());
            MoveUpField.setText(KeyEvent.getKeyText(moveUp));

        } else {
            moveUp = evt.getKeyCode();
        }
    }//GEN-LAST:event_MoveUpFieldKeyPressed

    private void MoveDownFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MoveDownFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SwingUtilities.invokeLater(() -> {
                boolean b = settings.setMoveDown(moveDown);
            });
            SwingUtilities.invokeLater(() -> setKeyBoard());
            MoveDownField.setText(KeyEvent.getKeyText(moveDown));
        } else {
            moveDown = evt.getKeyCode();
        }

    }//GEN-LAST:event_MoveDownFieldKeyPressed

    private void MoveLeftFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MoveLeftFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SwingUtilities.invokeLater(() -> {
                boolean b = settings.setMoveLeft(moveLeft);
            });
            SwingUtilities.invokeLater(() -> setKeyBoard());
            MoveLeftField.setText(KeyEvent.getKeyText(moveLeft));
        } else
            moveLeft = evt.getKeyCode();
    }//GEN-LAST:event_MoveLeftFieldKeyPressed

    private void MoveRightFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MoveRightFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SwingUtilities.invokeLater(() -> {
                boolean b = settings.setMoveRight(moveRight);
            });
            SwingUtilities.invokeLater(() -> setKeyBoard());
            MoveRightField.setText(KeyEvent.getKeyText(moveRight));
        } else
            moveRight = evt.getKeyCode();
    }//GEN-LAST:event_MoveRightFieldKeyPressed

    private void InteractFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_InteractFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SwingUtilities.invokeLater(() -> {
                boolean b = settings.setInteractButton(interact);
            });
            SwingUtilities.invokeLater(() -> setKeyBoard());
            InteractField.setText(KeyEvent.getKeyText(interact));
        } else
            interact = evt.getKeyCode();
    }//GEN-LAST:event_InteractFieldKeyPressed

    private void PauseFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_PauseFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SwingUtilities.invokeLater(() -> {
                boolean b = settings.setPauseButton(pause);
            });
            SwingUtilities.invokeLater(() -> setKeyBoard());
            PauseField.setText(KeyEvent.getKeyText(pause));
        } else
            pause = evt.getKeyCode();
    }//GEN-LAST:event_PauseFieldKeyPressed

    private void MapFieldKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_MapFieldKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SwingUtilities.invokeLater(() -> {
                boolean b = settings.setMapButton(map);
            });
            SwingUtilities.invokeLater(() -> setKeyBoard());
            MapField.setText(KeyEvent.getKeyText(map));
        } else
            map = evt.getKeyCode();
    }//GEN-LAST:event_MapFieldKeyPressed

    private void MoveUpFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MoveUpFieldMouseClicked
        SwingUtilities.invokeLater(() -> MoveUpField.setText(""));
    }//GEN-LAST:event_MoveUpFieldMouseClicked

    private void MoveDownFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MoveDownFieldMouseClicked
        SwingUtilities.invokeLater(() -> MoveDownField.setText(""));
    }//GEN-LAST:event_MoveDownFieldMouseClicked

    private void MoveLeftFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MoveLeftFieldMouseClicked
        SwingUtilities.invokeLater(() -> MoveLeftField.setText(""));
    }//GEN-LAST:event_MoveLeftFieldMouseClicked

    private void MoveRightFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MoveRightFieldMouseClicked
        SwingUtilities.invokeLater(() -> MoveRightField.setText(""));
    }//GEN-LAST:event_MoveRightFieldMouseClicked

    private void InteractFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_InteractFieldMouseClicked
        SwingUtilities.invokeLater(() -> InteractField.setText(""));
    }//GEN-LAST:event_InteractFieldMouseClicked

    private void PauseFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PauseFieldMouseClicked
       SwingUtilities.invokeLater(() -> PauseField.setText(""));
    }//GEN-LAST:event_PauseFieldMouseClicked

    private void MapFieldMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_MapFieldMouseClicked
        SwingUtilities.invokeLater(() -> MapField.setText(""));
    }//GEN-LAST:event_MapFieldMouseClicked

    private void ConversationTextAreaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ConversationTextAreaKeyTyped
        GuiManager.getInstance().hideDialog();
    }//GEN-LAST:event_ConversationTextAreaKeyTyped

    private void ConvDialogKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_ConvDialogKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            SwingUtilities.invokeLater(() -> ConvDialog.setVisible(false));
            SwingUtilities.invokeLater(() -> ConversationTextArea.setText(EMPTY_TEXT));
            SwingUtilities.invokeLater(() -> ConvDialog.setFocusable(false));
        }
    }//GEN-LAST:event_ConvDialogKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    protected javax.swing.JDialog AvatarChooserDialog;
    protected javax.swing.JPanel AvatarChooserPanel;
    protected javax.swing.JLabel AvatarIcon;
    protected javax.swing.JLabel AvatarImage;
    protected javax.swing.JTextField AvatarName;
    protected javax.swing.JLabel AvatarNameLabel;
    protected javax.swing.JButton AvatarOkButton;
    protected javax.swing.JButton CareerButtonFrame1;
    protected javax.swing.JDialog CareerDialog;
    protected javax.swing.JLabel CareerLabel;
    protected javax.swing.JPanel CareerPanel;
    protected javax.swing.JScrollPane CareerScrollPane;
    protected javax.swing.JButton ConfirmAnswer;
    public javax.swing.JDialog ConvDialog;
    public javax.swing.JScrollPane ConversationScrollPane;
    public javax.swing.JTextArea ConversationTextArea;
    protected javax.swing.JButton CreditsButton;
    protected javax.swing.JLabel EnergyIcon;
    protected javax.swing.JProgressBar EnergyProgressBar;
    protected javax.swing.JDialog ExamDialog;
    protected javax.swing.JPanel ExamPanel;
    protected javax.swing.JScrollPane ExamScrollPane;
    protected javax.swing.JTable ExamTable;
    protected javax.swing.JTextArea ExamTextArea;
    protected javax.swing.JButton ExitButton;
    protected javax.swing.JLabel ExitCareerDialog;
    protected javax.swing.JLabel ExitInventoryDialog;
    protected javax.swing.JLabel ExitQuestDialogLabel;
    protected javax.swing.JButton FemaleBlackButton;
    protected javax.swing.JButton FemaleWhiteButton;
    protected javax.swing.JButton FirstAnswer;
    protected javax.swing.JButton FourthAnswer;
    protected javax.swing.JButton GameCloseButton;
    public javax.swing.JDialog HintDialog;
    public javax.swing.JScrollPane HintScrollPane;
    public javax.swing.JTextArea HintTextArea;
    protected javax.swing.JPanel HudPanel;
    protected javax.swing.JLabel HungerIcon;
    protected javax.swing.JProgressBar HungerProgressBar;
    protected javax.swing.JFormattedTextField InteractField;
    protected javax.swing.JLabel InteractLabel;
    protected javax.swing.JButton InventoryButtonFrame;
    protected javax.swing.JDialog InventoryDialog;
    protected javax.swing.JLabel InventoryLabel;
    protected javax.swing.JPanel InventoryPanel;
    protected javax.swing.JTable InventoryTable;
    protected javax.swing.JPanel KeyBoardPanel;
    protected javax.swing.JButton KeyboardButton;
    protected javax.swing.JButton KeyboardSettingsCloseButton;
    protected javax.swing.JDialog KeyboardSettingsDialog;
    protected javax.swing.JLabel KeyboardSettingsLabel;
    protected javax.swing.JComboBox<String> LanguageComboBox;
    protected javax.swing.JPanel LeftBorder;
    protected javax.swing.JLabel LevelLabel;
    protected javax.swing.JLabel LevelOfQuestionLabel;
    protected javax.swing.JDialog MainMenuDialog;
    protected javax.swing.JLabel MainMenuLabel;
    protected javax.swing.JPanel MainMenuPanel;
    protected javax.swing.JButton MaleBlackButton;
    protected javax.swing.JButton MaleWhiteButton;
    protected javax.swing.JFormattedTextField MapField;
    protected javax.swing.JLabel MapLabel;
    protected javax.swing.JLabel MoneyIcon;
    protected javax.swing.JLabel MoneyLabel;
    protected javax.swing.JFormattedTextField MoveDownField;
    protected javax.swing.JLabel MoveDownLabel;
    protected javax.swing.JFormattedTextField MoveLeftField;
    protected javax.swing.JLabel MoveLeftLabel;
    protected javax.swing.JFormattedTextField MoveRightField;
    protected javax.swing.JLabel MoveRightLabel;
    protected javax.swing.JFormattedTextField MoveUpField;
    protected javax.swing.JLabel MoveUpLabel;
    protected javax.swing.JButton MusicButton;
    protected javax.swing.JLabel NameOfExamLabel;
    protected javax.swing.JButton NewGameButton;
    protected javax.swing.JButton NoButton;
    protected javax.swing.JTextField OpenInventoryField;
    protected javax.swing.JLabel OpenInventoryLabel;
    protected javax.swing.JFormattedTextField PauseField;
    protected javax.swing.JLabel PauseLabel;
    protected javax.swing.JLabel ProfLabel;
    protected javax.swing.JButton QuestButtonFrame;
    protected javax.swing.JDialog QuestDialog;
    protected javax.swing.JList<String> QuestList;
    protected javax.swing.JScrollPane QuestListScrollPane;
    protected javax.swing.JPanel QuestPanel;
    protected javax.swing.JTextArea QuestTextArea;
    protected javax.swing.JScrollPane QuestTextScrollPane;
    protected javax.swing.JDialog RequestDialog;
    protected javax.swing.JTextArea RequestLabel;
    protected javax.swing.JPanel RequestPanel;
    protected javax.swing.JScrollPane RequestScrollPane;
    protected javax.swing.JButton ResumeGameButton;
    protected javax.swing.JButton ReturnToMainMenuButton;
    protected javax.swing.JPanel RightBorder;
    protected javax.swing.JButton SecondAnswer;
    protected javax.swing.JButton SettingsButton;
    protected javax.swing.JButton SettingsButtonFrame;
    protected javax.swing.JButton SettingsCloseButton;
    protected javax.swing.JDialog SettingsDialog;
    protected javax.swing.JLabel SettingsLayer;
    protected javax.swing.JPanel SettingsPanel;
    protected javax.swing.JButton SoundButton;
    protected javax.swing.JLabel StressIcon;
    protected javax.swing.JProgressBar StressProgressBar;
    protected javax.swing.JLabel StudentLabel;
    protected javax.swing.JButton ThirdAnswer;
    protected final javax.swing.JLabel TimeLabel = new javax.swing.JLabel();
    protected javax.swing.JLabel TimeLabel1;
    protected javax.swing.JPanel UpperBorder;
    protected javax.swing.JButton YesButton;
    protected javax.swing.JLabel jLabel2;
    protected javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
