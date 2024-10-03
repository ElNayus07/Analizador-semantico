
import com.formdev.flatlaf.FlatIntelliJLaf; //Importa la clase FlatIntelliJLaf de la biblioteca FlatLaf, que permite aplicar un Look and Feel moderno y plano a la interfaz gráfica de usuario (GUI) en aplicaciones Swing.
import compilerTools.CodeBlock; //Importa la clase CodeBlock que probablemente se utiliza para representar bloques de código en el contexto de un compilador o intérprete.
import javax.swing.UIManager; // Importa la clase UIManager, que permite gestionar la apariencia y el comportamiento de los componentes Swing.
import javax.swing.UnsupportedLookAndFeelException; // Importa la excepción UnsupportedLookAndFeelException, que se lanza si se intenta establecer un Look and Feel que no es compatible.
import compilerTools.Directory; // Importa la clase Directory, posiblemente utilizada para manejar directorios en el contexto del compilador.
import compilerTools.ErrorLSSL; // Importa la clase ErrorLSSL, que probablemente maneja errores relacionados con el análisis sintáctico o semántico en el compilador.
import compilerTools.Functions; // Importa la clase Functions, que podría contener funciones útiles o definiciones de funciones utilizadas en el compilador.
import compilerTools.Grammar; // Importa la clase Grammar, que probablemente define las reglas gramaticales del lenguaje que está siendo procesado por el compilador.
import compilerTools.Production; // Importa la clase Production, que podría representar producciones en una gramática formal, esenciales para el análisis sintáctico.
import compilerTools.TextColor; // Importa la clase TextColor, posiblemente utilizada para manejar colores de texto en la interfaz gráfica o en salidas de texto del compilador.
import compilerTools.Token; // Importa la clase Token, que probablemente representa los tokens generados durante el proceso de análisis léxico del código fuente.
import java.awt.Color; // Importa la clase Color de AWT, utilizada para manejar colores en componentes gráficos.
import java.awt.event.ActionEvent; // Importa la clase ActionEvent, que representa un evento de acción, como un clic en un botón.
import java.awt.event.WindowAdapter; // Importa la clase WindowAdapter, una clase adaptadora para recibir eventos de ventana. Permite manejar eventos sin necesidad de implementar todos los métodos de la interfaz.
import java.awt.event.WindowEvent; // Importa la clase WindowEvent, que representa eventos relacionados con ventanas, como abrir, cerrar o activar una ventana.
import java.io.BufferedReader; // Importa la clase BufferedReader, utilizada para leer texto desde una entrada de caracteres, mejorando el rendimiento al leer datos en bloques.
import java.io.File; // Importa la clase File, que representa archivos y directorios en el sistema de archivos.
import java.io.FileInputStream; // Importa la clase FileInputStream, utilizada para leer bytes desde un archivo.
import java.io.FileNotFoundException; // Importa la excepción FileNotFoundException, lanzada cuando se intenta acceder a un archivo que no existe.
import java.io.FileOutputStream; // Importa la clase FileOutputStream, utilizada para escribir bytes a un archivo.
import java.io.IOException; // Importa la excepción IOException, lanzada cuando ocurre un error durante operaciones de entrada/salida.
import java.io.InputStreamReader; // Importa la clase InputStreamReader, que convierte bytes en caracteres utilizando una codificación específica.
import java.util.ArrayList; // Importa la clase ArrayList, una implementación dinámica de listas en Java, útil para almacenar colecciones de objetos.
import java.util.HashMap; // Importa la clase HashMap, una implementación de mapa basada en hash, utilizada para almacenar pares clave-valor eficientemente.
import javax.swing.JOptionPane; // Importa la clase JOptionPane, utilizada para mostrar cuadros de diálogo estándar, como mensajes y opciones al usuario.
import javax.swing.Timer; // Importa la clase Timer, utilizada para programar tareas que se ejecutan después de un cierto retraso o repetidamente con un intervalo específico

/**
 *
 * @author yisus
 */
public class Compilador extends javax.swing.JFrame {

    private String title;
    private Directory directorio;
    private ArrayList<Token> tokens;
    private ArrayList<ErrorLSSL> errors;
    private ArrayList<TextColor> textsColor;
    private Timer timerKeyReleased;
    private ArrayList<Production> identProd;
    private HashMap<String, String> identificadores;
    private boolean codeHasBeenCompiled = false;

    /**
     * Creates new form Compilador
     */
    public Compilador() {
        initComponents(); // Llama al método initComponents(), que generalmente se utiliza para inicializar los componentes de la interfaz gráfica (como botones, paneles, etc.). Este método es común en aplicaciones que utilizan el generador de GUI de Java.
        init(); //Llama al método init(), que se encarga de realizar configuraciones adicionales y inicializar variables y componentes específicos del compilador.
    }

    private void init() {
        title = "Compiler"; // "Compiler";: Asigna el valor "Compiler" a la variable title, que probablemente se usa para establecer el título de la ventana.
        setLocationRelativeTo(null); // Establece la ubicación de la ventana en el centro de la pantalla. El argumento null indica que debe centrarse en relación a la pantalla.
        setTitle(title); // Establece el título de la ventana con el valor almacenado en title.
        directorio = new Directory(this, jtpCode, title, ".comp"); //
        addWindowListener(new WindowAdapter() {// Cuando presiona la "X" de la esquina superior derecha
            @Override
            public void windowClosing(WindowEvent e) {
                directorio.Exit();
                System.exit(0);
            }
        });
        Functions.setLineNumberOnJTextComponent(jtpCode); // Llama a un método estático en la clase Functions, que probablemente agrega números de línea al componente jtpCode.
        timerKeyReleased = new Timer((int) (1000 * 0.3), (ActionEvent e) -> { 
            timerKeyReleased.stop(); // timerKeyReleased.stop();: Detiene el temporizador.
            colorAnalysis(); // Llama al método colorAnalysis(), que probablemente realiza algún tipo de análisis del código ingresado.
        });
        Functions.insertAsteriskInName(this, jtpCode, () -> {
            timerKeyReleased.restart(); // Llama al método colorAnalysis(), que probablemente realiza algún tipo de análisis del código ingresado.
        });
        tokens = new ArrayList<>(); // Crea una lista para almacenar tokens generados durante el análisis léxico.
        errors = new ArrayList<>(); // Crea una lista para almacenar errores encontrados durante el proceso.
        textsColor = new ArrayList<>(); // Crea una lista para almacenar información sobre colores aplicados al texto (posiblemente para resaltar sintaxis).
        identProd = new ArrayList<>(); // Crea una lista para almacenar identificadores producidos durante el análisis.
        identificadores = new HashMap<>(); // Crea un mapa para almacenar identificadores y sus valores asociados.
        Functions.setAutocompleterJTextComponent(new String[]{}, jtpCode, () -> {
            timerKeyReleased.restart(); // Se pasa un arreglo vacío como sugerencias iniciales y una expresión lambda que reinicia el temporizador cuando se activa el autocompletado.
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rootPanel = new javax.swing.JPanel();
        buttonsFilePanel = new javax.swing.JPanel();
        btnAbrir = new javax.swing.JButton();
        btnNuevo = new javax.swing.JButton();
        btnGuardar = new javax.swing.JButton();
        btnGuardarC = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtpCode = new javax.swing.JTextPane();
        panelButtonCompilerExecute = new javax.swing.JPanel();
        btnCompilar = new javax.swing.JButton();
        btnEjecutar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jtaOutputConsole = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblTokens = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        btnAbrir.setText("Abrir");
        btnAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAbrirActionPerformed(evt);
            }
        });

        btnNuevo.setText("Nuevo");
        btnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNuevoActionPerformed(evt);
            }
        });

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        btnGuardarC.setText("Guardar como");
        btnGuardarC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarCActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout buttonsFilePanelLayout = new javax.swing.GroupLayout(buttonsFilePanel);
        buttonsFilePanel.setLayout(buttonsFilePanelLayout);
        buttonsFilePanelLayout.setHorizontalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNuevo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnAbrir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnGuardarC)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        buttonsFilePanelLayout.setVerticalGroup(
            buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(buttonsFilePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(buttonsFilePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAbrir)
                    .addComponent(btnNuevo)
                    .addComponent(btnGuardar)
                    .addComponent(btnGuardarC))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jtpCode);

        btnCompilar.setText("Compilar");
        btnCompilar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCompilarActionPerformed(evt);
            }
        });

        btnEjecutar.setText("Ejecutar");
        btnEjecutar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEjecutarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelButtonCompilerExecuteLayout = new javax.swing.GroupLayout(panelButtonCompilerExecute);
        panelButtonCompilerExecute.setLayout(panelButtonCompilerExecuteLayout);
        panelButtonCompilerExecuteLayout.setHorizontalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCompilar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEjecutar)
                .addContainerGap())
        );
        panelButtonCompilerExecuteLayout.setVerticalGroup(
            panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelButtonCompilerExecuteLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelButtonCompilerExecuteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCompilar)
                    .addComponent(btnEjecutar))
                .addContainerGap(7, Short.MAX_VALUE))
        );

        jtaOutputConsole.setEditable(false);
        jtaOutputConsole.setBackground(new java.awt.Color(255, 255, 255));
        jtaOutputConsole.setColumns(20);
        jtaOutputConsole.setRows(5);
        jScrollPane2.setViewportView(jtaOutputConsole);

        tblTokens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Componente léxico", "Lexema", "[Línea, Columna]"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tblTokens.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(tblTokens);

        javax.swing.GroupLayout rootPanelLayout = new javax.swing.GroupLayout(rootPanel);
        rootPanel.setLayout(rootPanelLayout);
        rootPanelLayout.setHorizontalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, rootPanelLayout.createSequentialGroup()
                        .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 693, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 403, Short.MAX_VALUE)
                .addGap(17, 17, 17))
        );
        rootPanelLayout.setVerticalGroup(
            rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(rootPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonsFilePanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panelButtonCompilerExecute, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(rootPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(rootPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap(8, Short.MAX_VALUE))
        );

        getContentPane().add(rootPanel);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNuevoActionPerformed
        directorio.New(); //Llama al método New() del objeto directorio, que probablemente inicializa un nuevo documento o proyecto.
        clearFields(); //Llama al método clearFields(), que probablemente limpia los campos de entrada en la interfaz de usuario, preparando el espacio para nuevos datos.
    }//GEN-LAST:event_btnNuevoActionPerformed

    private void btnAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAbrirActionPerformed
        if (directorio.Open()) {
            colorAnalysis(); //Llama al método colorAnalysis(), que probablemente realiza algún tipo de análisis sobre el contenido del documento abierto, posiblemente relacionado con colores o formato.
            clearFields(); //Limpia los campos de entrada, similar a lo hecho en el método anterior.
        }
    }//GEN-LAST:event_btnAbrirActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        if (directorio.Save()) { // Intenta guardar el documento actual utilizando el método Save(). Si es exitoso, se procede a limpiar los campos.
            clearFields(); // Limpia los campos de entrada después de guardar.
        }
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnGuardarCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarCActionPerformed
        if (directorio.SaveAs()) { // Intenta guardar el documento actual con un nuevo nombre usando el método SaveAs(). Si tiene éxito, se limpian los campos.
            clearFields(); // Limpia los campos de entrada después de la operación de guardar como.
        }
    }//GEN-LAST:event_btnGuardarCActionPerformed

    private void btnCompilarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCompilarActionPerformed
        if (getTitle().contains("*") || getTitle().equals(title)) { // Verifica si el título del documento actual contiene un asterisco (*) o es igual al título original. Esto suele indicar que hay cambios no guardados.
            if (directorio.Save()) { //Si hay cambios no guardados, intenta guardar el documento actual antes de compilar usando el método Save().
                compile(); //Llama al método compile(), que probablemente realiza la acción de compilar el documento o proyecto.
            }
        } else { //Si no hay cambios no guardados, simplemente llama a compile() directamente.
            compile();
        }
    }//GEN-LAST:event_btnCompilarActionPerformed

    private void btnEjecutarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEjecutarActionPerformed
        btnCompilar.doClick(); //simula un clic en otro botón llamado btnCompilar. Esto probablemente inicia el proceso de compilación del código antes de ejecutarlo.
        if (codeHasBeenCompiled) { // Comprueba si el código ha sido compilado correctamente. codeHasBeenCompiled es una variable booleana que indica el estado de la compilación.
            if (!errors.isEmpty()) { // Verifica si hay errores en la lista errors. Si la lista no está vacía, significa que se encontraron errores durante la compilación.
                JOptionPane.showMessageDialog(null, "No se puede ejecutar el código ya que se encontró uno o más errores",
                        "Error en la compilación", JOptionPane.ERROR_MESSAGE); // Si hay errores, se muestra un cuadro de diálogo con un mensaje de error.
            } else { //
                CodeBlock codeBlock = Functions.splitCodeInCodeBlocks(tokens, "{", "}", ";"); // Si no hay errores, se procede a dividir el código en bloques.
                System.out.println(codeBlock); // Imprime en la consola el objeto codeBlock, lo cual puede ser útil para depuración.
                ArrayList<String> blocksOfCode = codeBlock.getBlocksOfCodeInOrderOfExec(); // Obtiene una lista ordenada de bloques de código listos para ser ejecutados mediante el método getBlocksOfCodeInOrderOfExec().
                System.out.println(blocksOfCode); // Imprime en la consola la lista de bloques de código, nuevamente útil para depuración.
                executeCode(blocksOfCode, 1); // Finalmente, llama al método executeCode, pasando la lista de bloques y un entero (presumiblemente un índice o contador) como argumentos. Este método se encargará de ejecutar los bloques en el orden adecuado.

            }
        }
    }//GEN-LAST:event_btnEjecutarActionPerformed
    
    private void executeCode(ArrayList<String> blocksOfCode, int repeats){
        for(int j=1; j<=repeats; j++){ //Iniciamos un bucle que se repetira desde 1 hasta el numero especificado
            int repeatCode = -1; // Inicializamos la variable repeatCode a -1 para utilizarla para almacenar el nuemero de repeticiones
            for (int i = 0; i < blocksOfCode.size(); i++){ //Comenzamos otro bucle que itera sobre cada bloque de codigo en blocksOfCode
                String blockOfCode = blocksOfCode.get(i); //Obtenemos el bloque de codigo actual en la iteracion
                if(repeatCode!= -1){ //Comprovamos si repeatCode ha sido establecido
                    int[] posicionMarcador = CodeBlock.getPositionOfBothMarkers(blocksOfCode, blockOfCode); // Llamamos a un metodo externo getPositionOfBothMarkers para obtener las posiciones de inicio y fin del bloque de repetición.
                    executeCode(new ArrayList<>(blocksOfCode.subList(posicionMarcador[0], posicionMarcador[1])), repeatCode); //Llama recursivamente al método executeCode con el sublistado correspondiente a las posiciones marcadas y el número de repeticiones.
                    repeatCode = -1; 
                    i = posicionMarcador[1]; //Restablece repeatCode a -1 y actualiza i para continuar después del bloque repetido.
                }
                else{
                    String[] sentences = blockOfCode.split(";"); //Si no hay un bloque de repetición, divide el bloque actual en sentencias usando ; como delimitador.
                    for (String sentence : sentences){ //Itera sobre cada sentencia, eliminando espacios en blanco al principio y al final.
                        sentence = sentence.trim(); 
                        if(sentence.startsWith("pintar")){
                            String parametro;
                            if(sentence.contains("$")){
                                parametro = identificadores.get(sentence.substring(9, sentence.length()-2));
                            }
                            else{
                                parametro = sentence.substring(9, sentence.length() -2);
                            }
                            System.out.println("Pintando de color"+ parametro+"..."); //Si la sentencia comienza con "pintar", extrae el parámetro correspondiente y lo imprime. Si contiene $, busca en un mapa llamado identificadores.
                        }
                        else if(sentence.startsWith("izquierda")){
                            System.out.println("Moviendose a la izquierda...");
                        }
                        else if(sentence.startsWith("derecha")){
                            System.out.println("Moviendose a la derecha...");
                        }
                        else if(sentence.startsWith("adelante")){
                            System.out.println("Moviendose a la adelante...");
                        }
                        else if(sentence.startsWith("-->")){
                            System.out.println("Declarando identificador...");
                        }
                        else if(sentence.startsWith("atras")){
                            System.out.println("Moviendose hacia atras..."); //Cada uno de estos bloques maneja diferentes comandos relacionados con el movimiento o declaración de identificadores, imprimiendo mensajes correspondientes.
                        }
                        else if(sentence.startsWith("repetir")){
                            String parametro;
                            if (sentence.contains("$")){
                                parametro = identificadores.get(sentence.substring(10, sentence.length() - 2));
                            }else{
                                parametro = sentence.substring(10, sentence.length() - 2);
                            }
                            repeatCode = Integer.parseInt(parametro); //Si la sentencia comienza con "repetir", extrae el parámetro y lo convierte a un entero, almacenándolo en repeatCode.
                        }
                    }
                }
            }
        }
    }
    
    private void compile() {
        clearFields(); // LLamamos al metodo clearFields que me limpiara los campos de las variables alamacenadas
        lexicalAnalysis(); // Invoca el método lexicalAnalysis(), que realiza el análisis léxico del código fuente.
        fillTableTokens(); // Llama al método fillTableTokens(), que probablemente llena una tabla o estructura de datos con los tokens generados en la etapa anterior.
        syntacticAnalysis(); // Ejecuta el método syntacticAnalysis(), donde se lleva a cabo el análisis sintáctico.
        semanticAnalysis(); // donde se verifica la semántica del código.
        printConsole(); //  Invoca el método printConsole(), que probablemente imprime información relevante sobre el proceso de compilación en la consola.
        codeHasBeenCompiled = true; //Establece la variable codeHasBeenCompiled en true. Esto indica que el proceso de compilación ha finalizado correctamente y puede ser utilizado más adelante para verificar si el código ha sido compilado.
    }

    private void lexicalAnalysis() {
        // Extraer tokens
        Lexer lexer; // Se declara una variable lexer de tipo Lexer. Este objeto se encargará de realizar el análisis léxico, es decir, dividir el texto en tokens significativos.
        try { // Inicia un bloque try para manejar excepciones que puedan ocurrir durante la ejecución del código que sigue.
            File codigo = new File("code.encrypter"); // Se crea un objeto File llamado codigo, que representa un archivo llamado "code.encrypter".
            FileOutputStream output = new FileOutputStream(codigo); // Se crea un FileOutputStream llamado output, que permite escribir en el archivo codigo.
            byte[] bytesText = jtpCode.getText().getBytes();
            output.write(bytesText); // Se obtiene el texto de un componente (probablemente un área de texto) llamado jtpCode, se convierte a un arreglo de bytes mediante getBytes(), y luego se escribe en el archivo utilizando output.write(bytesText).
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8")); //Se crea un BufferedReader llamado entrada, que permite leer el contenido del archivo codigo. Utiliza un InputStreamReader para leer los bytes como caracteres en formato UTF-8.
            lexer = new Lexer(entrada); // Se inicializa el objeto lexer con el BufferedReader entrada, permitiendo al lexer leer el contenido del archivo y comenzar a tokenizarlo.
            while (true) { // Se inicia un bucle infinito (while (true)).
                Token token = lexer.yylex(); // Se llama al método yylex() del lexer, que intenta obtener el siguiente token. Si no hay más tokens disponibles, devuelve null.
                if (token == null) { // Si se recibe un token (es decir, si no es null), este se añade a una colección llamada tokens.
                    break; // Si se recibe un token nulo, se sale del bucle (break).
                } 
                tokens.add(token);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage()); //Si no se puede encontrar el archivo especificado, se imprime un mensaje de error.
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage()); // Si ocurre algún error durante la escritura en el archivo, también se imprime un mensaje correspondiente.
        }
    }

    private void syntacticAnalysis() {
        Grammar gramatica = new Grammar(tokens, errors); //Crea una nueva instancia de la clase Grammar, inicializándola con tokens (tokens del código fuente) y errors (una lista para almacenar errores).
        
        /*Eliminacion de errores*/
        gramatica.delete(new String[]{"ERROR", "ERROR_1", "ERROR_2"},1); //Elimina tokens que son considerados errores en la gramática, especificando que estos errores tienen una prioridad o nivel de 1.
        
        /* Agrupacion de valores */
        gramatica.group("VALOR", "(NUMERO | COLOR)", true); //Agrupa los tokens que representan valores, permitiendo números o colores. El tercer parámetro true indica que esta agrupación es obligatoria.
        
        /* Declaracion de variables */
        gramatica.group("VARIABLE" , "TIPO_DATO IDENTIFICADOR OP_ASIG VALOR", true, identProd); //Agrupa declaraciones de variables que deben incluir un tipo de dato, un identificador, un operador de asignación y un valor.
        gramatica.group("VARIABLE" , "TIPO_DATO OP_ASIG VALOR", true,
                2, "error sintactico: falta el identificador en la variable [#, %]"); // La segunda línea permite una declaración sin identificador, pero genera un error sintáctico si falta el identificador.
        
        gramatica.finalLineColumn(); // Marca el final de una línea o columna en la gramática.
        
        gramatica.group("VARIABLE", "TIPO_DATO IDENTIFICADOR OP_ASIG",
                3,"error sintactico {}: falta el identificador en la variable [#, %]"); 
        
        gramatica.initialLineColumn(); // Agrupa nuevamente variables y verifica errores relacionados con la falta del identificador. Luego, reinicia el conteo de líneas y columnas.
        
        /*Eliminacion de tipos de dato y operadores de asignacion*/
        gramatica.delete("TIPO_DATO",4,
                "error sintactico{}: falta el identificador en la variable [#, %]");
        gramatica.delete("OP_ASIG",4,
                "error sintactico{}: falta el identificador en la variable [#, %]"); //Elimina los tokens de tipo de dato y operadores de asignación si no están correctamente utilizados.
        
        /*Agrupar identificadores y definicion de parametros*/
        gramatica.group("VALOR", "IDENTIFICADOR", true);
        gramatica.group("PARAMETROS", "VALOR(COMA VALOR)+", true); //Agrupa identificadores como valores y define parámetros como una lista de valores separados por comas.
        
        /* Agrupacion de Funciones */
        gramatica.group("FUNCION", "(MOVIMIENTO | PINTAR | DETENER_PINTAR | TOMAR |"
                + " LANZAR_MONEDA | VER | DETENER_REPETIR)", true); //Define las funciones disponibles en la gramática.
        gramatica.group("FUNCION_COMP", "FUNCION PARENTESIS_A (VALOR | PARAMETROS)? PARENTESIS_C", true); //Agrupa las funciones completas que pueden tener parámetros opcionales.
        gramatica.group("FUNCION_COMP", "FUNCION (VALOR | PARAMETROS)? PARENTESIS_C", true,
                6,"error_sintactico {}: falta el parentesis que abre la funcion[#,%]"); //Permite funciones sin paréntesis abiertos y genera un error si faltan.
        gramatica.finalLineColumn();
        
        gramatica.group("FUNCION_COMP", "FUNCION PARENTESIS_A (VALOR | PARAMETROS)", true,
                7, "error_sintactico {}: falta el parentesis que abre la funcion[#,%]");
        
        gramatica.initialLineColumn();
        
        /* Eliminiacion de funciones incompletas */
        gramatica.delete("FUNCION",8,"error sintactico {}: la funcion no esta declarada correctamente"); //Elimina funciones que no están correctamente declaradas.
        
        gramatica.loopForFunExecUntilChangeNotDetected(()->{
        gramatica.group("EXP_LOGICA", "(FUNCION_COMP | EXP_LOGICA) (OP_LOGICO (FUNCION_COMP | EXP_LOGICA))+");
        gramatica.group("EXP_LOGICA", "PARENTESIS_A (EXP_LOGICA | FUNCION_COMP) PARENTESIS_C");
        }); //Agrupa expresiones lógicas utilizando funciones y operadores lógicos. Utiliza un bucle para permitir múltiples agrupaciones hasta que no se detecten más cambios.

        /* ELiminacion de operadores logicos*/
        gramatica.delete("OP_LOGICO",10,
                "error sintactico{}: el operador logico no esta contenido en una expresion"); // Elimina operadores lógicos si no están contenidos dentro de expresiones válidas.
        
        /* Agrupacion de exp. logicas como valor y parametros */
        gramatica.group("VALOR", "EXP_LOGICA");
        gramatica.group("PARAMETROS", "VALOR (COMA VALOR)+");
        
        /* Agrupacion de estructuras de control */
        // Las siguientes líneas agrupan estructuras de control, eliminan estructuras incompletas y manejan errores relacionados con paréntesis y punto y coma:
        gramatica.group("EST_CONTROL", "(REPETIR | ESTRUCTURA_SI)");
        gramatica.group("EST_CONTROL_COMP", "EST_CONTROL PARENTESIS_A PARENTESIS_C");
        gramatica.group("EST_CONTROL_COMP", "EST_CONTROL (VALOR | PARAMETROS)");
        gramatica.group("EST_CONTROL_COMP", "EST_CONTROL PARENTESIS_A (VALOR | PARAMETROS) PARENTESIS_C"); 
        
        /* ELIMINACION DE ESTRUCTURAS DE CONTROL INCOMPLETAS */
        gramatica.delete("EST_CONTROL",11,
                "error sintactico {}: La estructura de control no esta declarada correctamente [#,%]");
        
        /* Eliminacion de parentesis */
        gramatica.delete(new String []{"PARENTESIS_A","PARENTESIS_C"},12,
            "error sintactico {}: el parentesis [] no esta contenido en una agrupacion [#, %]");
        
        gramatica.finalLineColumn();
        
        /* Verificacion de punto y coma al final de una sentencia */
        //Identificadores o variables
        gramatica.group("VARIABLE_PC", "VARIABLE PUNTO_COMA",true);
        gramatica.group("VARIABLE_PC", "VARIABLE",true,
                13, "error sintactico {}: Falta el punto y coma final de la variable [#, %]");
        
        //Funciones
        gramatica.group("FUNCION_COMP_PC", "FUNCION_COMP PUNTO_COMA");
        gramatica.group("FUNCION_COMP_PC", "FUNCION_COMP", 14,
                "error sintactico {}: falta el punto y coma al final de la declaracion de la funcion"); 
        
        gramatica.initialLineColumn();
        
        /* Eliminacion del punto y coma */
        gramatica.delete("PUNTO_COMA",15,
                "error sintactico {}: el punto y coma no esta al final de una sentencia"); //Se verifica que las sentencias terminen correctamente con un punto y coma
        
        //Finalmente, se agrupan todas las sentencias válidas
        /* Sentencias */
        gramatica.group("SENTENCIAS", "(VARIABLE_PC | FUNCION_COMP_PC)+");
        
        /*  */
        gramatica.loopForFunExecUntilChangeNotDetected(()->{
            gramatica.group("EST_CONTROL_COMP_LASLC","EST_CONTROL_COMP LLAVE_A (SENTENCIAS)? LLAVE_C",true);
            gramatica.group("SENTENCIAS","(SENTENCIAS | EST_CONTROL_COMP_LASLC)+");
        });
        
        /* Estructuras de funcion incompletas */
        gramatica.loopForFunExecUntilChangeNotDetected(()->{
            gramatica.initialLineColumn();
            
            gramatica.group("EST_CONTROL_COMP_LASLC", "EST_CONTROL_COMP (SENTENCIAS)? LLAVE_C",true,
                    15, "error sintactico {}: falta la llave que abre en la estructura de control");
            
            gramatica.finalLineColumn();
            
            gramatica.group("EST_CONTROL_COMP_LASLC", "EST_CONTROL_COMP LLAVE_A SENTENCIAS",true,
                    15, "error sintactico {}: falta la llave que cierra en la estructura de control");
            
            gramatica.group("SENTENCIAS","(SENTENCIAS | EST_CONTROL_COMP_LASLC)");
        });
        
        gramatica.delete(new String[]{"LLAVE_A","LLAVE_C"},16,
                "error sintactico {}: la llave {} no esta contenida en una agrupacion [#, %]");
        
        
        /* Mostrar gramáticas */
        gramatica.show(); //gramatica.show();: Muestra la gramática resultante después del análisis sintáctico.
    }

    private void semanticAnalysis() {
        HashMap<String, String> identDataType = new HashMap<>(); // Se crea un nuevo objeto HashMap llamado identDataType. Este mapa se utilizará para asociar identificadores
        identDataType.put("color", "COLOR"); //La clave "color" se asocia con el valor "COLOR".
        identDataType.put("número", "NUMERO"); //La clave "número" se asocia con el valor "NUMERO".

        for(Production id: identProd){ // Se inicia un bucle que itera sobre una colección llamada identProd, 
            if (!identDataType.get(id.lexemeRank(0)).equals(id.lexicalCompRank(-1))){ //Se verifica si el tipo de dato asociado al identificador (obtenido mediante id.lexemeRank(0)) es diferente del tipo de dato que se encuentra en la producción (obtenido mediante id.lexicalCompRank(-1)). Si son diferentes, se considera que hay un error semántico.
                errors.add(new ErrorLSSL(1 , "error semantico {}: valor no compatible con el tipo de dato", id, true)); //Si hay un error, se crea una nueva instancia de ErrorLSSL con un código de error 1, un mensaje de error que incluye el identificador problemático (id), y se añade a la lista errors.
            }
            else if (id.lexicalCompRank(-1).equals("COLOR") && !id.lexemeRank(-1).matches("#[0-9a-fA-F]+")){  // Si el tipo de dato es "COLOR", se verifica si el valor proporcionado (obtenido mediante id.lexemeRank(-1)) coincide con el patrón hexadecimal (un string que comienza con # seguido de dígitos hexadecimales). Si no coincide, se considera otro error semántico.
                errors.add(new ErrorLSSL(2 , "error semantico {}: el color no es un numero hexadecimal", id, false)); //Si el valor no es un número hexadecimal válido, se crea otra instancia de ErrorLSSL con un código de error 2 y se añade a la lista errors.
            }
            else{
                identificadores.put(id.lexemeRank(1), id.lexemeRank(-1)); //Si no hay errores semánticos, se almacena el identificador en otro mapa llamado identificadores. La clave es el resultado de id.lexemeRank(1) y el valor es el resultado de id.lexemeRank(-1).
            }
        }
    }

    private void colorAnalysis() {
        /* Limpiar el arreglo de colores */
        textsColor.clear(); //Se limpia el arreglo textsColor, que probablemente almacena los colores asociados a diferentes partes del texto.
        /* Extraer rangos de colores */
        LexerColor lexerColor; //Se declara una variable lexerColor de tipo LexerColor, que se utilizará para analizar el texto y extraer información sobre los colores.
        try {
            File codigo = new File("color.encrypter"); 
            FileOutputStream output = new FileOutputStream(codigo); //Se crea un objeto File llamado codigo, que representa un archivo llamado "color.encrypter". Luego, se crea un FileOutputStream para escribir datos en este archivo.
            byte[] bytesText = jtpCode.getText().getBytes(); // jtpCode.getText(): Obtiene el texto del componente jtpCode, que es presumiblemente un área de texto.
            output.write(bytesText); // Escribe el arreglo de bytes en el archivo "color.encrypter".
            BufferedReader entrada = new BufferedReader(new InputStreamReader(new FileInputStream(codigo), "UTF8")); // Se crea un BufferedReader llamado entrada, que permite leer el contenido del archivo "color.encrypter". Se utiliza un InputStreamReader para especificar que la codificación del archivo es UTF-8.
            lexerColor = new LexerColor(entrada); // Se inicializa el objeto lexerColor con el BufferedReader. Este objeto se encargará de analizar el contenido del texto y extraer información sobre los colores.
            while (true) { //Se inicia un bucle infinito (while (true)).
                TextColor textColor = lexerColor.yylex(); // Dentro del bucle, se llama al método yylex() de lexerColor, que devuelve un objeto TextColor. Este objeto representa un color asociado a una parte del texto.
                if (textColor == null) { //Si yylex() devuelve null, significa que no hay más colores por analizar, y se rompe el bucle.
                    break;
                }
                textsColor.add(textColor); //Si se obtiene un objeto TextColor, se agrega al arreglo textsColor.
            }
        } catch (FileNotFoundException ex) {
            System.out.println("El archivo no pudo ser encontrado... " + ex.getMessage()); //Captura si no se encuentra el archivo especificado.
        } catch (IOException ex) {
            System.out.println("Error al escribir en el archivo... " + ex.getMessage()); // Captura errores relacionados con la entrada/salida, como problemas al escribir en el archivo.
        }
        Functions.colorTextPane(textsColor, jtpCode, new Color(40, 40, 40));// Finalmente, se llama al método estático colorTextPane de la clase Functions. 
    }

    private void fillTableTokens() {
        tokens.forEach(token -> { //colección (como una lista o un conjunto) que contiene objetos de tipo token.
            Object[] data = new Object[]{token.getLexicalComp(), token.getLexeme(), "[" + token.getLine() + ", " + token.getColumn() + "]"}; //Se declara un arreglo de objetos llamado data.
            Functions.addRowDataInTable(tblTokens, data); // Este es un llamado a un método estático (o de instancia) en la clase Functions.
        });
    }

    private void printConsole() {
        int sizeErrors = errors.size(); //Aquí se inicializa una variable entera sizeErrors que almacena el tamaño de la lista errors. 
        if (sizeErrors > 0) { //Se evalúa si hay errores en la lista. Si sizeErrors es mayor que 0, significa que hay errores que se deben procesar y mostrar.
            Functions.sortErrorsByLineAndColumn(errors); //Se llama a un método estático sortErrorsByLineAndColumn de la clase Functions, que ordena los errores en la lista errors según su número de línea y columna. Esto facilita la lectura y análisis de los errores.
            String strErrors = "\n"; //Se inicializa una cadena strErrors comenzando con un salto de línea. Esta cadena se utilizará para acumular todos los mensajes de error.
            for (ErrorLSSL error : errors) { // Se inicia un bucle for-each para iterar sobre cada objeto error en la lista errors. Aquí, ErrorLSSL es probablemente una clase que representa un error específico.
                String strError = String.valueOf(error); // Cada objeto error se convierte a su representación en cadena mediante String.valueOf(), y se almacena en la variable strError.
                strErrors += strError + "\n"; // Se agrega el mensaje de error (strError) a la cadena strErrors, seguido de un salto de línea. Esto crea una lista acumulada de todos los errores.
            }
            jtaOutputConsole.setText("Compilación terminada...\n" + strErrors + "\nLa compilación terminó con errores..."); //Se establece el texto del componente jtaOutputConsole, que parece ser un área de texto en la interfaz gráfica.
        } else { // se ejecuta si no hay errores
            jtaOutputConsole.setText("Compilación terminada..."); //Si no hay errores, se establece un mensaje simple en el área de texto indicando que la compilación ha terminado sin problemas.
        }
        jtaOutputConsole.setCaretPosition(0); // Finalmente, se restablece la posición del cursor al inicio del área de texto (jtaOutputConsole). Esto asegura que el usuario vea el principio del mensaje al finalizar la compilación.
    }

    private void clearFields() {
        Functions.clearDataInTable(tblTokens); //Functions.clearDataInTable(tblTokens);: Llama a un método estático clearDataInTable de la clase Functions, pasando como argumento tblTokens.
        jtaOutputConsole.setText(""); // Establece el texto del área de texto
        tokens.clear(); // Llama al método clear() en la lista tokens, eliminando todos los elementos que contiene.
        errors.clear(); // Similar al anterior, este comando limpia la lista errors, eliminando todos los errores registrados.
        identProd.clear(); // Limpia la lista identProd, que probablemente almacena identificadores de producción o similares.
        identificadores.clear(); //Finalmente, este comando limpia la lista identificadores, eliminando todos sus elementos.
        codeHasBeenCompiled = false; //Establece la variable booleana
    }

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
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Compilador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(new FlatIntelliJLaf());
            } catch (UnsupportedLookAndFeelException ex) {
                System.out.println("LookAndFeel no soportado: " + ex);
            }
            new Compilador().setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAbrir;
    private javax.swing.JButton btnCompilar;
    private javax.swing.JButton btnEjecutar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnGuardarC;
    private javax.swing.JButton btnNuevo;
    private javax.swing.JPanel buttonsFilePanel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jtaOutputConsole;
    private javax.swing.JTextPane jtpCode;
    private javax.swing.JPanel panelButtonCompilerExecute;
    private javax.swing.JPanel rootPanel;
    private javax.swing.JTable tblTokens;
    // End of variables declaration//GEN-END:variables
}
