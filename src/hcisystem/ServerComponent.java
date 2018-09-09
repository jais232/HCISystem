/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcisystem;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author pra
 */
public class ServerComponent {

    
    private static final int PORT = 8539;
    
    static BufferedReader br;
    private static BufferedWriter bw;
    static Socket socket;
    
    private final static String CLOSE_CONNECTON_MESSAGE = "$$CLOSE_CONNECTION$$";
    
    private final static int SERVER_STARTED = 0;
    private final static int WAITING_FOR_CLIENTS = 1;
    private final static int CLIENT_CONNECTED = 2;
    private final static int SERVER_DISCONNECTED = 3;
    
    //constants for modes
    private static final String INSIDE_VOICE_MODE = "$$voice_mode_selected$$";
    private static final String INSIDE_GESTURE_MODE = "$$gesture_mode_selected$$";
    private static final String INSIDE_IMAGE_MODE = "$$image_mode_selected$$";
    private static final String INSIDE_GAMING_MODE = "$$gaming_mode_selected$$";
    private static final String INSIDE_KEYBOARD_MODE = "$$keyboard_mode_selected$$";

    
    public static JFrame mainWindow;
    private JLabel serverConnectionStatusMessage;
    public static JLabel voiceMsg;
    public static JLabel sensorReading;
    public JLabel ipShow;
    public static ImageIcon imageIcon;
    //private JButton serverStartButton;
    //private JButton serverStopButton;
    
    public static String IPADDRESS;
    
    public ServerComponent() {
        
    }
    
    public void startServerComponent() {
    
        System.out.println("setting up server window the GUIs");
        //setup GUI
        setServerWindowGUI();
        startServerThread();
    }
    
    private void setServerWindowGUI() {
    
        System.out.println("started GUI build");
        //JFrame properties
        mainWindow = new JFrame("HCI APPLICATION");
        mainWindow.setSize(300, 300);
        mainWindow.setResizable(false);
        mainWindow.setLocationRelativeTo(null);
        mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        
        //Layout definition
        BorderLayout mainwindowBorder = new BorderLayout();
        mainWindow.setLayout(mainwindowBorder);
        
        //JPanel definition
        JPanel leftPanel = new JPanel();
        JPanel rightPanel = new JPanel();
        JPanel newPanel = new JPanel();
        rightPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        newPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        leftPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        rightPanel.setBorder(BorderFactory.createLineBorder(Color.black));
        
        //Jlabel definition
        serverConnectionStatusMessage = new JLabel("");
        voiceMsg = new JLabel("");
        sensorReading = new JLabel("");
        ipShow = new JLabel("");
        
        //ImageIcon definition
        imageIcon = new ImageIcon();
        
        
        /*
        //button definition
        serverStartButton = new JButton("START SERVER");
        serverStartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            
                System.out.println("starting server thread.");
                serverStartButton.setEnabled(false);
                startServerThread();
            }
        });
        */
        /*
        serverStopButton = new JButton("STOP SERVER");
        serverStopButton.setEnabled(false);
        serverStopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
            
                sendStopConnectionMessage();
            }
        });
        */
        
        //add components to JPanel
        //leftPanel.add(serverStartButton);
        //leftPanel.add(serverStopButton);
        leftPanel.add(serverConnectionStatusMessage);
        //rightPanel.add(voiceMsg);
        //rightPanel.add(sensorReading);
        newPanel.add(ipShow);
        rightPanel.add(new JLabel(imageIcon));
        
        
        //add components to Jframe
        mainWindow.add(leftPanel, BorderLayout.NORTH);
        mainWindow.add(newPanel, BorderLayout.SOUTH);
        mainWindow.add(rightPanel, BorderLayout.CENTER);
        
        
        //This line always at the last. damn you.
        mainWindow.setVisible(true);
        System.out.println("GUI build complete.");
        
    }
    
    void startServerThread() {
    
        Thread serverThread = new Thread() {
            @Override
            public void run() {
                
                while(true) {
                    try {
                        System.out.println("Server starting at port number: " + PORT);
                        ServerSocket serverSocket = null;
                        setConnectionStatusMessage(SERVER_STARTED);
                        serverSocket = new ServerSocket(PORT);

                        InetAddress addr = InetAddress.getLocalHost();
                        IPADDRESS = addr.getHostAddress();
                        createQR(IPADDRESS);
                        //ipShow.setText(IPADDRESS);

                        setConnectionStatusMessage(WAITING_FOR_CLIENTS);
                        //client connecting
                        System.out.println("Waiting for client to connect");
                        socket = serverSocket.accept();
                        //client connected
                        System.out.println("client has connected");
                        setConnectionStatusMessage(CLIENT_CONNECTED);

                        br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                        bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

                        //receiving messages from client
                        String data;

                        while((data = br.readLine()) != null) {

                            if(data.equals(CLOSE_CONNECTON_MESSAGE)) {

                                System.out.println("received close connection message");
                                sendStopConnectionMessage();
                                System.out.println("sent close connection message to client");
                                break;
                            }
                            else {

                                System.out.println("message from client: " + data);
                                switch (data) {
                                    case INSIDE_VOICE_MODE:
                                        System.out.println("voice module selected");
                                        gettingInsideVoiceModule();
                                        break;
                                    case INSIDE_GESTURE_MODE:
                                        System.out.println("gesture module selected");
                                        gettingInsideGestureModule();
                                        break;
                                    case INSIDE_IMAGE_MODE:
                                        System.out.println("image module selected");
                                        gettingInsideImageModule();
                                        break;
                                    case INSIDE_GAMING_MODE:
                                        System.out.println("gaming module selected");
                                        gettingInsideGamingModule();
                                        break;
                                    case INSIDE_KEYBOARD_MODE:
                                        System.out.println("keyboard module selected");
                                        gettingInsideKeyboardModule();
                                        break;
                                    default:
                                        System.out.println("Command not recognised");
                                        break;
                                }
                            }

                        }
                        System.out.println("closing readers writers and socket.");
                        bw.close();
                        br.close();
                        socket.close();
                        serverSocket.close();
                        setConnectionStatusMessage(SERVER_DISCONNECTED);
                        System.out.println("Server has ended");


                    } catch (IOException ex) {
                        Logger.getLogger(ServerComponent.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }

                
            }
        
            
        };
        serverThread.start();
         
    }
    
    private void setConnectionStatusMessage(int statusCode) {
    
        switch(statusCode) {
        
            case SERVER_STARTED:
                serverConnectionStatusMessage.setText("Server is started.");
                break;
                
            case WAITING_FOR_CLIENTS:
                serverConnectionStatusMessage.setText("Waiting for client to connect.");
                break;
                
            case CLIENT_CONNECTED:
                serverConnectionStatusMessage.setText("Client is connected.");
                //performConnectionUpActions();
                break;
                
            case SERVER_DISCONNECTED:
                serverConnectionStatusMessage.setText("Server is stopped.");
                //performConnectionDownActions();
                break;
            
        }
    }
        
    /*
    private void performConnectionUpActions() {
    
        serverStopButton.setEnabled(true);
        
        
    }
    
    private void performConnectionDownActions() {
    
        serverStartButton.setEnabled(true);
        serverStopButton.setEnabled(false);
    }
    */
    private void sendStopConnectionMessage() {
    
        try {
            bw.write(CLOSE_CONNECTON_MESSAGE);
            bw.newLine();
            bw.flush();
        } catch (IOException ex) {
            Logger.getLogger(ServerComponent.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (Exception e) {
                    
            System.out.println(e.getMessage());
        }
    }
    
    private void gettingInsideVoiceModule() throws Exception {
    
        try {
        
            System.out.println("Going to VoiceModuleComponent");
            VoiceModuleComponent voiceModule = new VoiceModuleComponent(new ServerComponent());
            voiceModule.startVoiceProcessing();
        } catch(Exception e) {
        
            System.out.println(e.getMessage());
        }
        
    }
    
    private void gettingInsideGestureModule() throws Exception {
    
        try {
        
            System.out.println("Going to GestureModuleComponent");
            GestureModuleComponent gestureModule = new GestureModuleComponent(new ServerComponent());
            gestureModule.getSensorReadings();
        } catch(Exception e) {
        
            System.out.println(e.getMessage());
        }
        
    }
    
    private void gettingInsideImageModule() {
    
        try {
            
            System.out.println("Going to ImageModuleComponent");
            ImageModuleComponent imageModule = new ImageModuleComponent(new ServerComponent());
            imageModule.startImageProcessing();
        } catch(Exception e) {
        
            System.out.println(e.getMessage());
        }
        
    }
    
    private void gettingInsideGamingModule() {
    
        try {
            
            System.out.println("Going to GameModuleComponent");
            GameModuleComponent gmc = new GameModuleComponent();
            gmc.receiveCommands();
        } catch(Exception e) {
        
            System.out.println(e.getMessage());
        }
        
    }
    
    private void gettingInsideKeyboardModule() {
    
        try {
            
            System.out.println("Going to KeyboardModuleComponent");
            KeyboardModuleComponent kmc = new KeyboardModuleComponent();
            kmc.typeIt();
            //we will decide it later
        } catch(Exception e) {
        
            System.out.println(e.getMessage());
        }
        
    }
    
    public void createQR(String address) {
		
        int size = 250;
	
        try {
	
            Map<EncodeHintType, Object> hintMap = new EnumMap<>(EncodeHintType.class);
	    hintMap.put(EncodeHintType.CHARACTER_SET, "UTF-8");
	
            hintMap.put(EncodeHintType.MARGIN, 1); /* default = 4 */
	    hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
            QRCodeWriter qrCodeWriter = new QRCodeWriter();
	
            BitMatrix byteMatrix = qrCodeWriter.encode(address, BarcodeFormat.QR_CODE, size, size, hintMap);
	
            int CrunchifyWidth = byteMatrix.getWidth();
	    BufferedImage image = new BufferedImage(CrunchifyWidth, CrunchifyWidth, BufferedImage.TYPE_INT_RGB);
	    image.createGraphics();
 
            Graphics2D graphics = (Graphics2D) image.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, CrunchifyWidth, CrunchifyWidth);
            graphics.setColor(Color.BLACK);
 
            for (int i = 0; i < CrunchifyWidth; i++) {
	
                for (int j = 0; j < CrunchifyWidth; j++) {
		
                    if (byteMatrix.get(i, j)) {
		
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            
            imageIcon.setImage(image);
            ipShow.setText("Your IP adress is : " + address);
            mainWindow.pack();
        } catch (Exception e) {
	
            System.out.println(e.getMessage());
        } 
	
        System.out.println("\n\nYou have successfully created QR Code.");
	
    }
    
}
