package com.example.mijuegodepalabras;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView questionTextView;
    private Button answer1Button;
    private Button answer2Button;
    private Button answer3Button;
    private TextView resultTextView;

    private String[] questions = {
            "¿Qué significa CPU?",
            "¿Qué significa RAM?",
            "¿Cuál es la representación binaria del número 10?",
            "¿Qué es un algoritmo?",
            "¿Qué es un compilador?",
            "¿Cuál es la función del sistema operativo?",
            "¿Qué es la programación orientada a objetos?",
            "¿Qué es un bucle 'for'?",
            "¿Qué es un servidor?",
            "¿Qué es un archivo comprimido?"
    };

    private String[] answers = {
            "Unidad Central de Procesamiento",
            "Memoria de Acceso Aleatorio",
            "1010",
            "Un conjunto de instrucciones para resolver un problema",
            "Un programa que traduce código fuente a código ejecutable",
            "Administrar recursos y proporcionar una interfaz de usuario",
            "Un paradigma de programación basado en objetos y clases",
            "Una estructura de control para repetir un bloque de código un número específico de veces",
            "Un equipo que proporciona servicios a otros equipos en una red",
            "Un archivo que contiene uno o más archivos y/o directorios comprimidos"
    };

    private String[][] incorrectAnswers = {
            {"Central Personal de Usuarios", "Conjunto de Procesamiento Unificado"},
            {"Memoria de Lectura Automática", "Memoria de Acceso Directo"},
            {"1111", "0101"},
            {"Un conjunto de programas predefinidos", "Una representación gráfica de datos"},
            {"Un programa que ejecuta código en la web", "Un software que organiza archivos en un disco duro"},
            {"Ejecutar aplicaciones de entretenimiento", "Optimizar el rendimiento de la memoria RAM"},
            {"Un enfoque de programación basado en líneas de código", "Un método para ejecutar instrucciones secuenciales"},
            {"Una estructura de control para ejecutar código una vez", "Una forma de conectar dispositivos a Internet"},
            {"Un equipo que almacena datos de forma permanente", "Una aplicación para editar imágenes"},
            {"Un tipo de documento de texto", "Un archivo que contiene música y vídeos"}
    };

    private int currentIndex = 0;
    private int correctAnswersCount = 0;
    private int incorrectAnswersCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        questionTextView = findViewById(R.id.questionTextView);
        answer1Button = findViewById(R.id.answer1Button);
        answer2Button = findViewById(R.id.answer2Button);
        answer3Button = findViewById(R.id.answer3Button);
        resultTextView = findViewById(R.id.resultTextView);

        showQuestion(currentIndex);

        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(0);
            }
        });

        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(1);
            }
        });

        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(2);
            }
        });
    }

    private void showQuestion(int index) {
        questionTextView.setText(questions[index]);
        answer1Button.setText(answers[index]);
        answer2Button.setText(incorrectAnswers[index][0]);
        answer3Button.setText(incorrectAnswers[index][1]);
    }

    private void checkAnswer(int selectedAnswerIndex) {
        // Verifica si la respuesta seleccionada es igual a la respuesta correcta
        if (selectedAnswerIndex == 0 && answer1Button.getText().equals(answers[currentIndex])) {
            correctAnswersCount++;
        } else {
            incorrectAnswersCount++;
        }

        // Mueve a la siguiente pregunta si aún quedan preguntas por responder
        if (currentIndex < questions.length - 1) {
            currentIndex++;
            showQuestion(currentIndex);
        } else {
            // Muestra el resultado al finalizar todas las preguntas
            showResult();
        }
    }

    private void showResult() {
        String message;
        if (correctAnswersCount == questions.length) {
            message = "¡Eres un genio!";
        } else if (correctAnswersCount >= (questions.length * 0.7)) {
            message = "¡Muy bien, excelente trabajo!";
        } else if (correctAnswersCount >= (questions.length * 0.5)) {
            message = "Puedes hacerlo mejor. ¡Sigue intentando!";
        } else {
            message = "¡Debes practicar más!";
        }
        resultTextView.setText(message + "\nRespuestas correctas: " + correctAnswersCount +
                "\nRespuestas incorrectas: " + incorrectAnswersCount);
    }
}
