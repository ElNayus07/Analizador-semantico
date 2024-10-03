
import jflex.exceptions.SilentExit; // Esta clase se utiliza para manejar excepciones específicas que pueden ocurrir durante la ejecución de JFlex, específicamente cuando se produce un error que provoca una salida silenciosa (sin mensajes de error visibles).

/**
 *
 * @author yisus
 */
public class ExecuteJFlex { //Esta es la clase principal que contiene el método main, el punto de entrada para cualquier aplicación Java.

    public static void main(String omega[]) { // Este es el método main, que es el punto de inicio de la ejecución del programa. 
        String lexerFile = System.getProperty("user.dir") + "/src/Lexer.flex", 
                lexerFileColor = System.getProperty("user.dir") + "/src/LexerColor.flex"; //Esto permite que el programa sea más flexible y portátil, ya que utiliza rutas relativas basadas en el directorio actual.
        try { 
            jflex.Main.generate(new String[]{lexerFile, lexerFileColor}); //Este método intenta generar los analizadores léxicos basados en los archivos especificados.
        } catch (SilentExit ex) { //Si ocurre una excepción del tipo SilentExit, se captura aquí.
            System.out.println("Error al compilar/generar el archivo flex: " + ex); // Si se captura una excepción, esta línea imprime un mensaje de error en la consola, indicando que hubo un problema al compilar o generar los archivos Flex. El objeto ex proporciona información sobre la excepción.
        }
    }
}
