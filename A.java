import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class A extends JFrame implements ActionListener, MouseListener {
    private JPanel LeftPanel = new JPanel();
    private JPanel RightPanel = new JPanel();
    private JLabel leftlabel;
    private String[] cols = {"No.","Date","Customer","Total"};
    private String[][] Data = {
            {"1","22-11-2020","Ali","15000"},
            {"2","13-10-2021","Saleh","12000"},
    };
    DefaultTableModel x = new DefaultTableModel(Data,cols);
    private JTable InvoicesTable ;
    private JButton CreateNewInvoice;
    private JButton DeleteInvoice;
    private JMenuBar menuBar;
    private JMenu FileMenu;
    private JMenuItem LoadFile;
    private JMenuItem SaveFile;

    private JLabel InvoiceNumber;
    private JTextField IN;
    private JLabel InvoiceDate;
    private JTextField ID;
    private JLabel CustomerName;
    private JTextField CN;
    private JLabel InvoiceTotal;
    private JTextField IT;
    private JTable InvoiceItems;
    private String[] COL = {"No.","Item Name","Item Price","Count"," Total"};
    private String[][] data ={
            {"1","Mobile","3200","1","1000"},
            {"1","Cover","20","2","2000"},
            {"1","Headphone","130","1","3000"},
            {"2","Laptop","4000","1","4000"},
            {"2","Mouse","35","1","5000"}
    };
    private JLabel Invoiceitems;
    private JButton Save;
    private JButton Cancel;

    public A() {
        super("SIG");
        setSize(1500,800);
        setLocation(100,100);
        this.setVisible(true);
        setLayout(new GridLayout(1,1));
        LeftPanel.setBackground(Color.GRAY);
        RightPanel.setBackground(Color.pink);
        this.add(LeftPanel);
        this.add(RightPanel);
        LeftPanel.setLayout(null);
        RightPanel.setLayout(null);



        leftlabel = new JLabel("Invoices Table");
        leftlabel.setBounds(40,40,180,20);
        LeftPanel.add(leftlabel);

        InvoicesTable = new JTable(x);
        InvoicesTable.setBounds(40,60,670,550);
        LeftPanel.add(InvoicesTable);

        CreateNewInvoice = new JButton("Create New Invoice");
        CreateNewInvoice.setBounds(150,650,180,20);
        LeftPanel.add(CreateNewInvoice);


        DeleteInvoice = new JButton("Delete Invoice");
        DeleteInvoice.setBounds(350,650,180,20);
        LeftPanel.add(DeleteInvoice);

        menuBar = new JMenuBar();
        FileMenu = new JMenu("File");
        LoadFile = new JMenuItem("Load File"); LoadFile.setActionCommand("LF");
        SaveFile = new JMenuItem("Save File"); SaveFile.setActionCommand("SF");
        FileMenu.add(LoadFile);
        FileMenu.add(SaveFile);
        menuBar.add(FileMenu);
        setJMenuBar(menuBar);

        InvoiceNumber = new JLabel("Invoice Number");
        InvoiceNumber.setBounds(40,40,180,20);
        RightPanel.add(InvoiceNumber);

        IN = new JTextField(33);
        IN.setEditable(false);
        IN.setBounds(160,40,180,20);
        RightPanel.add(IN);

        InvoiceDate = new JLabel("Invoice Date");
        InvoiceDate.setBounds(40,80,180,20);
        RightPanel.add(InvoiceDate);

        ID = new JTextField(33);
        ID.setBounds(160,80,180,20);
        RightPanel.add(ID);

        CustomerName = new JLabel("Customer Name");
        CustomerName.setBounds(40,120,180,20);
        RightPanel.add(CustomerName);

        CN = new JTextField(33);
        CN.setBounds(160,120,180,20);
        RightPanel.add(CN);

        InvoiceTotal = new JLabel("Invoice Total");
        InvoiceTotal.setBounds(40,160,180,20);
        RightPanel.add(InvoiceTotal);

        IT = new JTextField(33);
        IT.setEditable(false);
        IT.setBounds(160,160,180,20);
        RightPanel.add(IT);

        Invoiceitems = new JLabel("Invoice Items");
        Invoiceitems.setBounds(40,210,180,20);
        RightPanel.add(Invoiceitems);

        InvoiceItems = new JTable(data,COL);
        InvoiceItems.setBounds(40,230,600,350);
        RightPanel.add(InvoiceItems);

        Save = new JButton("Save");
        Save.setBounds(200,650,180,20);
        RightPanel.add(Save);

        Cancel = new JButton("Cancel");
        Cancel.setBounds(400,650,180,20);
        RightPanel.add(Cancel);

        CreateNewInvoice.addActionListener(this);
        DeleteInvoice.addActionListener(this);
        InvoicesTable.addMouseListener(this);
        LoadFile.addMouseListener(this);





        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == CreateNewInvoice){
        String[][] z = {{"","","",""}};
        x.addRow(z);
        }
        if(InvoicesTable.getSelectedRowCount() == 1){
          x.removeRow(InvoicesTable.getSelectedRow());
        }
        switch (e.getActionCommand()){
            case "LF" :
                try {
                    loadFile();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "SF" :
                saveFile();
                break;
        }

    }
    private void loadFile() throws IOException {
        JFileChooser fc = new JFileChooser();
        int result = fc.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            String path = fc.getSelectedFile().getPath();
            FileInputStream fis = new FileInputStream(path);
            int size = fis.available();
            byte[] b = new byte[size];
            fis.read(b);
            x.setColumnIdentifiers(cols);
            fis.close();
        }

    }
    private void saveFile(){

    }

    public static void main(String[] args) {
        A x = new A();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int selectedRowIndex = InvoicesTable.getSelectedRow();

        IN.setText(x.getValueAt(selectedRowIndex,0).toString());
        ID.setText(x.getValueAt(selectedRowIndex,1).toString());
        CN.setText(x.getValueAt(selectedRowIndex,2).toString());
        IT.setText(x.getValueAt(selectedRowIndex,3).toString());

        }



    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}


