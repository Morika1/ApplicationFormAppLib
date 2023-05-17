# ApplicationFormAppLib

# Application Form

A simple application form to leave details with the following fields:
Name 
Phone 
Email 
Address 
Additional info

![details](https://github.com/Morika1/ApplicationFormAppLib/assets/68543807/085d702c-18ea-4e85-a849-a479e6a6cf81)

# Setup

Step 1. Add the maven repository in your settings.gradle under the section:

	dependencyResolutionManagement {
    		repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
   			 repositories {
      				maven { url 'https://jitpack.io' }
    			}
		}

Step 2. Add the dependency to your module:app build.gradle file:

	dependencies {
	        implementation 'com.github.Morika1:ApplicationFormAppLib:Tag'
	}



# Usage

	public class MainActivity extends AppCompatActivity {

		Callback_DetailsProtocol callback_detailsProtocol = new Callback_DetailsProtocol() {
			@Override
			public void useDetails(ArrayList<String> details) {
			// use details. details[0] = name, details[1] = phone, details[2] = email, details[3] = address , details[4] = additional info
			Log.d("In callback", "ready to use details");
			}
		};

		@Override
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.activity_main);

			ConstraintLayout mainLay = findViewById(R.id.main_LAY_main); // main_LAY_main is the id of the activity_main.xml layout

			ApplicationForm.get().setCallback_detailsProtocol(callback_detailsProtocol);
			ApplicationForm.get().generateForm(this, mainLay);
		}
	}


# Credits

Glide library in usage to load form's background image.
