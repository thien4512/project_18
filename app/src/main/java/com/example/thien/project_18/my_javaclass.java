package com.example.thien.project_18;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response;
import com.android.volley.Request;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class my_javaclass extends AppCompatActivity {

    // These are the global variables
    EditText editName, editPassword;
    TextView result;
    Button buttonSubmit, buttonReset;

   // EditText etGitHubUser; // This will be a reference to our GitHub username input.
    //Button btnGetRepos;  // This is a reference to the "Get Repos" button.
    TextView tvRepoList, hienthi;  // This will reference our repo list text box.
    RequestQueue requestQueue;  // This is our requests queue to process our HTTP requests.

    String baseUrl = "";  // This is the API base URL (GitHub API)
    String url;  // This will hold the full URL which will include the username entered in the etGitHubUser.
    String response_server="";
    String input_user;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_layout);

        editName  = (EditText) findViewById(R.id.editName);
        editPassword = (EditText) findViewById(R.id.editPassword);
        result = (TextView) findViewById(R.id.tvResult);
        buttonSubmit = (Button) findViewById(R.id.buttonSubmit);
        buttonReset = (Button) findViewById(R.id.buttonReset);
            /*-------------*/
        //this.etGitHubUser =  (EditText) findViewById(R.id.et_github_user);
       // this.btnGetRepos = (Button) findViewById(R.id.btn_get_repos);
        this.tvRepoList = (TextView) findViewById(R.id.tv_repo_list);
        this.tvRepoList.setMovementMethod(new ScrollingMovementMethod());
        this.hienthi = (TextView) findViewById(R.id.hienthi);
        requestQueue = Volley.newRequestQueue(this);
        /*-----------*/

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // Get the Data and Use it
// get text from EditText name view
                String name = editName.getText().toString();
// get text from EditText password view
                String password = editPassword.getText().toString();
                //result.setText("Name:\t" + name + "\nPassword:\t" + password );
                baseUrl = "http://starglobal.xyz/demo/iot/rest/application.php/get_login?user="+name+"&password="+password;

                /*---- gửi tới server------*/
                // Clear the repo list (so we have a fresh screen to add to)
                clearRepoList();
                // Call our getRepoList() function that is defined above and pass in the
                // text which has been entered into the etGitHubUser text input field.
                getRepoList(input_user);
                /*---- End gửi tới server------*/

                /*---------
                if(response_server.equals("OK"))
                {
                    a=1;
                    result.setText("Đăng nhập thành công");

                }
                else {
                    result.setText("sai rồi nhé :))");

                    a=0;
                }

                if(a==1)
                {
                    //setContentView(R.layout.my_layout_2);
                    //result.setText("Đăng nhập thành công\nhi thien4512");
                    Intent intent = new Intent(my_javaclass.this, MainActivity.class);
                    startActivity(intent);
                }                   */
            }
        });
    /*
    Using the id of views specified in layout XML
    we can initialize the view using findViewById
*/


// Attaching OnClick listener to the submit button



        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // clearing out all the values
                editName.setText("");
                editPassword.setText("");
                result.setText("");
                editName.requestFocus();
            }
        });
    }




    /*-----------*/
    private void clearRepoList() {
        // This will clear the repo list (set it as a blank string).
        this.tvRepoList.setText("");
    }

    private void addToRepoList(String repoName) {
        // This will add a new repo to our list.
        // It combines the repoName and lastUpdated strings together.
        // And then adds them followed by a new line (\n\n make two new lines).
        String strRow = repoName;
        String currentText = tvRepoList.getText().toString();
        this.tvRepoList.setText(currentText + "\n\n" + strRow);
    }

    private void setRepoListText(String str) {
        // This is used for setting the text of our repo list box to a specific string.
        // We will use this to write a "No repos found" message if the user doens't have any.
        this.tvRepoList.setText(str);
    }

    private void getRepoList(String username) {
        // First, we insert the username into the repo url.
        // The repo url is defined in GitHubs API docs (https://developer.github.com/v3/repos/).
        //this.url = this.baseUrl+ username + "/repos";
        this.url = this.baseUrl;

        // Next, we create a new JsonArrayRequest. This will use Volley to make a HTTP request
        // that expects a JSON Array Response.
        // To fully understand this, I'd recommend readng the office docs: https://developer.android.com/training/volley/index.html

        JsonArrayRequest arrReq = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        //Check the length of our response (to see if the user has any repos)
                        if (response.length() > 0) {
                            // The user does have repos, so let's loop through them all.
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    // For each repo, add a new line to our repo list.
                                    JSONObject jsonObj = response.getJSONObject(i);
                                    String repoName = jsonObj.get("ketqua").toString();
                                    //String lastUpdated = jsonObj.get("updated_at").toString();
                                    addToRepoList(repoName);
                                    //hienthi.setText(repoName);
                                    response_server=repoName;

                                    /*--------so sánh----------*/
                                    if(response_server.equals("OK"))
                                    {
                                        a=1;
                                        result.setText("Đăng nhập thành công");

                                    }
                                    else {
                                        result.setText("sai rồi nhé :))");

                                        a=0;
                                    }

                                    if(a==1)
                                    {
                                        //setContentView(R.layout.my_layout_2);
                                        //result.setText("Đăng nhập thành công\nhi thien4512");
                                        Intent intent = new Intent(my_javaclass.this, MainActivity.class);
                                        startActivity(intent);
                                    }

                                    /*--------End so sánh----------*/

                                } catch (JSONException e) {
                                    // If there is an error then output this to the logs.
                                    Log.e("Volley", "Invalid JSON Object.");
                                }

                            }
                        } else {
                            // The user didn't have any repos.
                            setRepoListText("No repos found.");
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // If there a HTTP error then add a note to our repo list.
                        setRepoListText("Error while calling REST API");
                        Log.e("Volley", error.toString());
                    }
                }
        );
        // Add the request we just defined to our request queue.
        // The request queue will automatically handle the request as soon as it can.
        requestQueue.add(arrReq);
    }
    /*
    public void getReposClicked(View v) {
        // Clear the repo list (so we have a fresh screen to add to)
        clearRepoList();
        // Call our getRepoList() function that is defined above and pass in the
        // text which has been entered into the etGitHubUser text input field.
        getRepoList(etGitHubUser.getText().toString());
    }           */
    /*------------*/

}
