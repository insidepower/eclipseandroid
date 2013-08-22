[ EditText ]

EditText et_passwd = (EditText) findViewById(R.id.txt_password);
String passwd = et_passwd.getText().toString();
if (passwd.equals("03012007")) {
[ ------------------------------------------------------------------ ]

=== [ java:topic ][ String format ][ print ] ===
et_passwd.setText(String.format("%.02f", 0.05));
