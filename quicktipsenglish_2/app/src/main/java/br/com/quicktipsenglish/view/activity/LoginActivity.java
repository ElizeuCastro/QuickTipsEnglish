package br.com.quicktipsenglish.view.activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import br.com.quicktipsenglish.R;
import br.com.quicktipsenglish.view.AuthenticationView;
import br.com.quicktipsenglish.view.presenter.AuthenticationPresenter;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, AuthenticationView {

    private Button btnContentLogin, btnContentRegister, btnLogin, btnRegister;
    private EditText edtLogin, edtPassword, edtRegisterEmail, edtRegisterLogin, edtRegisterPassword;
    private LinearLayout containerLogin, containerRegister;
    private AuthenticationPresenter presenter;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        dialog = new ProgressDialog(this);
        dialog.setCancelable(false);
        presenter = new AuthenticationPresenter(this);

        btnContentLogin = (Button) findViewById(R.id.btn_container_login);
        btnContentLogin.setOnClickListener(this);
        btnContentRegister = (Button) findViewById(R.id.btn_container_register);
        btnContentRegister.setOnClickListener(this);

        containerLogin = (LinearLayout) findViewById(R.id.container_login);
        containerRegister = (LinearLayout) findViewById(R.id.container_register);

        edtLogin = (EditText) containerLogin.findViewById(R.id.edt_login);
        edtPassword = (EditText) containerLogin.findViewById(R.id.edt_password);
        btnLogin = (Button) containerLogin.findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);

        edtRegisterEmail = (EditText) containerRegister.findViewById(R.id.edt_register_email);
        edtRegisterLogin = (EditText) containerRegister.findViewById(R.id.edt_register_login);
        edtRegisterPassword = (EditText) containerRegister.findViewById(R.id.edt_register_password);
        btnRegister = (Button) containerRegister.findViewById(R.id.btn_register);
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_container_login:
                containerLogin.setVisibility(View.VISIBLE);
                containerRegister.setVisibility(View.GONE);
                break;
            case R.id.btn_container_register:
                containerLogin.setVisibility(View.GONE);
                containerRegister.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_login:
                dialog.setMessage("Wait, authenticating user...");
                dialog.show();
                presenter.login(edtLogin.getText().toString(), edtPassword.getText().toString());
                break;
            case R.id.btn_register:
                dialog.setMessage("Wait, registering user...");
                dialog.show();
                presenter.register(edtRegisterEmail.getText().toString(), edtRegisterLogin.getText().toString(),
                        edtRegisterPassword.getText().toString());
                break;
            default:
                break;
        }

    }

    @Override
    public void loginFail() {

    }

    @Override
    public void loginSuccess() {

    }

    @Override
    public void registerFail() {
        Toast.makeText(this, "Register fail", Toast.LENGTH_LONG).show();
    }

    @Override
    public void registerSuccess() {
        Toast.makeText(this, "Register success", Toast.LENGTH_LONG).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
