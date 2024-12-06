//imports
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public class Main {
    public static void main(String[] args) {
        //Variables
        ArrayList<Integer> listaCPU = new ArrayList<>();//Lista que almacena la secuencia ingresada por la cpu.
        ArrayList<Integer> listaUsuario = new ArrayList<>();//Lista que almacena la secuencia ingresada por el usuario.
        Random random = new Random();//Numero aleatorio que va a ser utilizado para simular la secuencia de la cpu.
        AtomicBoolean gameOver = new AtomicBoolean(false);//Variable de control que valida si el juego se debe seguir ejecutando.

        //Configuración de la ventana
        JFrame ventana = new JFrame("Simon");//Creación de la ventana principal del juego.
        ventana.setSize(850, 850);//Dimensiones de la ventana
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Hace que el programa deje de ejecutarse al cerrarse la ventana.
        ventana.getContentPane().setBackground(Color.black);//Le da el color del fondo a la ventana.
        ventana.setLayout(null);//Establece el layout de la ventana, en este caso se deja en null.

        //Configuración del label textoTitulo.
        JLabel textoTitulo = new JLabel("Simon");//Se crea una instancia de Jlabel llamada textoTitulo. Contiene el texo "Simon".
        textoTitulo.setForeground(Color.white);//Establece el color del texto.
        textoTitulo.setFont(new Font("Times New Roman", Font.PLAIN, 90));//Se establece el tipo y tamaño de la letra.

        //Configuración del label textoInstrucciones.
        JLabel textoInstrucciones = new JLabel("Para jugar utilice las teclas W,A,S,D.");
        textoInstrucciones.setForeground(Color.white);
        textoInstrucciones.setFont(new Font("Times New Roman", Font.PLAIN, 35));
        
        //Configuración del panel que contiene el título.
        JPanel tituloPanel = new JPanel();//Se crea una instancia de JPanel para el titulo ("Simon").
        tituloPanel.setBounds(100, 100, 600, 150);//Se le asigna la ubicación al panel.
        tituloPanel.setBackground(Color.black);//Se le otorga el color del fondo al panel.
        tituloPanel.add(textoTitulo);//Se le agrega el Jlabel textoTitulo al panel (para que muestre el texto "Simon").

        //Configuración del panel que contiene las instrucciones.
        JPanel instruccionesPanel = new JPanel();
        instruccionesPanel.setBounds(50, 715, 730, 150);
        instruccionesPanel.setBackground(Color.black);
        instruccionesPanel.add(textoInstrucciones);

        //Creación de los botones
        JButton boton1 = crearBoton(new Color(125, 25, 25));
        JButton boton2 = crearBoton(new Color(155, 162, 12));
        JButton boton3 = crearBoton(new Color(8, 40, 138));
        JButton boton4 = crearBoton(new Color(36, 149, 18));

        //Creación de los paneles para los botones.
        JPanel boton1Panel = crearPanelBoton(boton1, 300, 400);
        JPanel boton2Panel = crearPanelBoton(boton2, 300, 600);
        JPanel boton3Panel = crearPanelBoton(boton3, 100, 500);
        JPanel boton4Panel = crearPanelBoton(boton4, 500, 500);

        //Se agregan los paneles ed los botones y del titulo a la ventana para que sean visibles.
        ventana.add(tituloPanel);
        ventana.add(instruccionesPanel);
        ventana.add(boton1Panel);
        ventana.add(boton2Panel);
        ventana.add(boton3Panel);
        ventana.add(boton4Panel);

        //Se hace visible la ventana
        ventana.setVisible(true);

        while (!gameOver.get()) {
            //Genera un núnero aleatorio y lo agrega a la listaCPU.
            listaCPU.add(random.nextInt(4) + 1);

            //Muestra la secuencia de la cpu, presionando los botones de forma automática.
            mostrarSecuencia(listaCPU, boton1, boton2, boton3, boton4);

            //Limpia listaUsuario para cada nueva iteración.
            listaUsuario.clear(); 

            //Asigna ActionListener a los botones para controlar lo que debe suceder en cada caso.
            ActionListener cambiarColorTemporal = e -> {

                //Comprobación para saber si se debe detener el ActionListener.
                if (gameOver.get()) return; //Si gameOver es true, sale del ActionListener mediante el return.

                //Variables
                JButton boton = (JButton) e.getSource();//Se almacena el boton que activó el evento en la variable boton.
                int entradaUsuario = 0;//Variable que sirve para almacenar los botones que presionó el usuario en la listaUsuario.

                //Ifs anidaos que cambian el color del boton, le asignan un valor a entradaUsuario dependiendo de cual boton se haya presionado y reproucen el sonido indicado.
                if (boton == boton1) {
                    boton.setBackground(Color.red);
                    entradaUsuario = 1;
                    new Thread(() -> reproducirSonido(440, 730)).start();
                } else if (boton == boton2) {
                    boton.setBackground(Color.yellow);
                    entradaUsuario = 2;
                    new Thread(() -> reproducirSonido(554.365f, 730)).start();
                } else if (boton == boton3) {
                    boton.setBackground(Color.blue);
                    entradaUsuario = 3;
                    new Thread(() -> reproducirSonido(659.255f, 730)).start();
                } else if (boton == boton4) {
                    boton.setBackground(Color.green);
                    entradaUsuario = 4;
                    new Thread(() -> reproducirSonido(329.628f, 730)).start();
                }

                listaUsuario.add(entradaUsuario);//Se agrega a la listaUsuario el número del botón que ingresó el usuario.

                int indice = listaUsuario.size() - 1;//Variable para conseguir el valor del indice deseado en la listaUsuario y listaCPU.
                //Comprueba si la entrada del usuario coincide con la secuencia de la CPU hasta el momento
                if (!listaUsuario.get(indice).equals(listaCPU.get(indice))) {
                    gameOver.set(true);//Cambia el valor de gameOver a true para indicar que el juego debe terminar.
                }

                //Restablecer el color después de 1 segundo.
                new Timer(300, revertirColor -> {//Creación de un nuevo timer, se activa luego de 1 segundo.
                    boton.setBackground(boton.getForeground());//Se devuelve el color original al boton (el foreground nunca se cambió, y tenía el mismo color que el background).
                    ((Timer) revertirColor.getSource()).stop();//Detiene el timer para que no se siga ejecutando.
                }).start();//Comienza el timer.
                
            };

            //Asigna ActionListener a cada botón
            asignarEvento(boton1, "W", cambiarColorTemporal);
            asignarEvento(boton2, "S", cambiarColorTemporal);
            asignarEvento(boton3, "A", cambiarColorTemporal);
            asignarEvento(boton4, "D", cambiarColorTemporal);

            //Pausa mientras se espera la entrada del usuario
            while (!gameOver.get() && listaUsuario.size() < listaCPU.size()) {
                try {
                    Thread.sleep(100); //Pausar para reducir uso de la CPU
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
            
        }
        //Se cambia el título del juego dentro de la pantalla por la puntuación.
        textoTitulo.setText("Puntaje: " + (listaCPU.size() - 1));
    }

    // Método para mostrar la secuencia de CPU pulsando automáticamente los botones
    private static void mostrarSecuencia(ArrayList<Integer> listaCPU, JButton boton1, JButton boton2, JButton boton3, JButton boton4) {
        Timer timer = new Timer(1100, new ActionListener() {//Creación de un timer.
            int indice = 0;//Variable que determina el indice/posición en las listas.

            //Se sobreescribe el método predefinido de ActionListener, actionPerformed.
            @Override
            public void actionPerformed(ActionEvent e) {
                if (indice >= listaCPU.size()) {//If que controla si ya se presionaron todos los botones que había en la listaCPU.
                    ((Timer) e.getSource()).stop();//Se edtiene el evento.
                    return;//Se sale del método.
                }

                JButton botonActual;//Variable que va a referenciar el boton que se presionó.
                double  iluminacionSegundos = 0.20000000 / Math.log10(10 * Math.pow(listaCPU.size(), 2));//Se calcula el tiempo de iluminación del boton en segundos.
                iluminacionSegundos*= 1000;//Se pasa ese tiempo a milisegundos.
                final int tiempo=(int) iluminacionSegundos;//Se almacena el tiemp en una variable entera final, para poder unsarse en los Timer.
                //Switch case que determina cuál botón fue presionado.
                switch (listaCPU.get(indice)) {
                    
                    case 1 -> {//En caso de que haya sifo el boton 1...
                        botonActual = boton1;//Se hace referencia al boton1.
                        botonActual.setBackground(Color.red);//Se cambia el color del boton1.
                        new Thread(() -> reproducirSonido(440, tiempo)).start();//Se crea un nuevo hilo que invoca al método reproducirSonido.
                    }
                    case 2 -> {
                        botonActual = boton2;
                        botonActual.setBackground(Color.yellow);
                        new Thread(() -> reproducirSonido(554.365f,tiempo)).start();
                    }
                    case 3 -> {
                        botonActual = boton3;
                        botonActual.setBackground(Color.blue);
                        new Thread(() -> reproducirSonido(659.255f, tiempo)).start();               
                    }
                    default -> {
                        botonActual = boton4;
                        botonActual.setBackground(Color.green);
                        new Thread(() -> reproducirSonido(329.628f,tiempo)).start();
                    } 
                }             
                
                // Restablece el color del botón después de un tiempo.
                new Timer(tiempo, evt -> {
                    botonActual.setBackground(botonActual.getForeground());//Se devuelve el color original al boton
                    ((Timer) evt.getSource()).stop();//Se detiene el timer.
                }).start();

                indice++;//Se aumenta la variable indice en +1.
            }
        });
        timer.start();//Comienza el timer
    }

    //Método que permite reproducir el sonido deseado.
    public static void reproducirSonido(float frequency, double duration) {
        byte[] buffer = new byte[1];
        AudioFormat audioFormat = new AudioFormat(44100, 8, 1, true, true);
        try (SourceDataLine line = javax.sound.sampled.AudioSystem.getSourceDataLine(audioFormat)) {
            line.open(audioFormat);
            line.start();
            for (int i = 0; i < duration * 44100 / 1000; i++) {
                double angle = 2.0 * Math.PI * i / (44100 / frequency);
                buffer[0] = (byte) (Math.sin(angle) * 127);
                line.write(buffer, 0, 1);
            }
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    //Método que asigna una tecla al evento. (Cuando el inputMap detecta una tecla valida, ejecuta el actionMap)
    private static void asignarEvento(JButton boton, String tecla, ActionListener listener) {
        boton.addActionListener(listener);//Se le agrega un listener al boton.
        boton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(tecla), tecla);//Almacena la tecla que se presionó.
        boton.getActionMap().put(tecla, new AbstractAction() {//Obtiene el actionMap.
            @Override//Se sobreescribe el metodo actionPerformed
            public void actionPerformed(ActionEvent e) {//Método que se ejecuta al activarse la tecla.
                listener.actionPerformed(new ActionEvent(boton, ActionEvent.ACTION_PERFORMED, null));//Se ejecuta el listener.
            }
        });
    }

    //Método que se encarga de crear los botones.
    private static JButton crearBoton(Color color) {
        JButton boton = new JButton("        ");//Creación del boton.
        boton.setBackground(color);//Asignación de color.
        boton.setForeground(color);//Asignación de color.
        boton.setFont(new Font("Times New Roman", Font.PLAIN, 40));//Creación y asignación de fuente.
        boton.setFocusPainted(false);//Se elimina el rectángulo blanco que aparece por defecto en los botones.
        return boton;//Devuelve el boton.
    }

    //Método que se encarga de crear los paneles para los botones.
    private static JPanel crearPanelBoton(JButton boton, int x, int y) {
        JPanel panel = new JPanel();//Creación del panel.
        panel.setBounds(x, y, 200, 100);
        panel.setBackground(Color.black);
        panel.add(boton);
        return panel;
    }
}