package com.example.prac_login;

import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText etEmail, etPassword;
    private ImageButton btnTogglePassword;
    private Button btnLogin;
    private TextView tvForgot, tvRegister;
    private boolean passwordVisible = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        btnTogglePassword = findViewById(R.id.btnTogglePassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvForgot = findViewById(R.id.tvForgot);
        tvRegister = findViewById(R.id.tvRegister);

        btnTogglePassword.setOnClickListener(v -> togglePassword());
        btnLogin.setOnClickListener(v -> attemptLogin());
        tvForgot.setOnClickListener(v ->
                Toast.makeText(this, "Recuperar contraseña", Toast.LENGTH_SHORT).show());
        tvRegister.setOnClickListener(v ->
                Toast.makeText(this, "Crear cuenta", Toast.LENGTH_SHORT).show());
    }

    private void togglePassword() {
        passwordVisible = !passwordVisible;
        if (passwordVisible) {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
        } else {
            etPassword.setInputType(InputType.TYPE_CLASS_TEXT
                    | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        }
        etPassword.setSelection(etPassword.getText().length());
    }

    private void attemptLogin() {
        String email = etEmail.getText().toString().trim();
        String password = etPassword.getText().toString();

        if (email.isEmpty()) {
            etEmail.setError("Ingresa tu correo");
            etEmail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("Correo no válido");
            etEmail.requestFocus();
            return;
        }
        if (password.length() < 6) {
            etPassword.setError("Mínimo 6 caracteres");
            etPassword.requestFocus();
            return;
        }

        // Aquí iría la autenticación real (API, Firebase, etc.)
        Toast.makeText(this, "Inicio de sesión correcto", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
