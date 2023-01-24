import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {

    //to create a frame
    JFrame frame;

    //to create menu
    JMenuBar menubar;

    //to add menus to the menubar
    JMenu file,edit,view;

    //to add menu items to file menu
    JMenuItem newfile,open,save,close;

    //to add menu items to edit menu
    JMenuItem cut,copy,paste,selectall;

    JMenuItem increase,decrease;

    //to add a text area to the frame
    JTextArea textArea;

    TextEditor(){

        //create a frame set the frame bounds
        frame =new JFrame();
        frame.setBounds(100,100,720,540);

        //create textarea and add it to the frame
        textArea =new JTextArea();
        frame.add(textArea);

        //create menubar
        menubar =new JMenuBar();

        //create menus
        file =new JMenu("File");
        edit =new JMenu("Edit");
        view =new JMenu("view");

        //adding menus to the menubar
        menubar.add(file);
        menubar.add(edit);
        // menubar.add(view);

        //setting the frame visiblity and menubar
        frame.setVisible(true);
        frame.setJMenuBar(menubar);

        //creating menulist for file menu
        newfile =new JMenuItem("New");
        open =new JMenuItem("Open");
        save =new JMenuItem("Save");
        close =new JMenuItem("Close");

        //adding menuitems to file menu
        file.add(newfile);
        file.add(open);
        file.add(save);
        file.add(close);

        //adding actionlisterners to menuitems in file menu
        newfile.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        close.addActionListener(this);

        //creating menuitems for edit menu
        cut =new JMenuItem("Cut");
        copy =new JMenuItem("Copy");
        paste =new JMenuItem("Paste");
        selectall =new JMenuItem("Select All");

        //adding menuitems to the edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectall);

        //adding action listerners to the menuitems
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectall.addActionListener(this);

        increase =new JMenuItem("Increase Font Size");
        decrease =new JMenuItem("Decrease Font Size");

        view.add(increase);
        view.add(decrease);

        increase.addActionListener(this);
        decrease.addActionListener(this);

    }

    //overriding the actionperformend method
    @Override
    public void actionPerformed(ActionEvent e)
    {
        //to copy the select text
        if(e.getSource()==copy)
        {
            textArea.copy();
        }
        //to cut the selected text
        if(e.getSource()==cut)
        {
            textArea.cut();
        }
        //to paste the copied text/clipboard
        if(e.getSource()==paste)
        {
            textArea.paste();
        }
        //to select everything from the textarea
        if (e.getSource()==selectall)
        {
            textArea.selectAll();
        }
        //to create new text file
        if(e.getSource()==newfile)
        {
            TextEditor neweditor=new TextEditor();
        }
        //to close the text file
        if(e.getSource()==close) {
            System.exit(0);
        }
        //to open a text file which is already present
        if (e.getSource()==open)
        {
            //jfilechooser to open the filechooser window
            JFileChooser fileChooser=new JFileChooser("desktop");
            //to save the option selected in the filechooser windown (open 1 /cancel 0 )
            int choose=fileChooser.showOpenDialog(null);
            //if open is selected
            if(choose==JFileChooser.APPROVE_OPTION)
            {
                //to select the file
                File file=fileChooser.getSelectedFile();

                try {
                    //to read the file in the selected path using bufferedreader
                    BufferedReader reader=new BufferedReader(new FileReader(file.getAbsolutePath()));
                    String output="" , cur="";

                    while((cur = reader.readLine())!=null)
                    {
                        output+=cur+"\n";
                    }

                    //set the textarea of the app to the output
                    textArea.setText(output);

                }catch (Exception exception){
                    //to handle exception
                    exception.printStackTrace();
                }
            }
        }
        if(e.getSource()==save)
        {
            //to open the filechooser window
            JFileChooser fileChooser=new JFileChooser("desktop");
            //to store the option selected (save 1 / cancel 0)
            int chose=fileChooser.showSaveDialog(null);
            if(chose==JFileChooser.APPROVE_OPTION)
            {
                //create a new file to store content
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                try
                {
                    //write the content using bufferedwriter
                    BufferedWriter writer=new BufferedWriter(new FileWriter(file));
                    textArea.write(writer);
                    writer.close();
                }catch (Exception exception)
                {
                    //to handel exception
                    exception.printStackTrace();
                }
            }
        }
        if(e.getSource()==increase)
        {
            Font font=textArea.getFont();
        }

    }

    public static void main(String[] args) {

        TextEditor text=new TextEditor();
    }
}