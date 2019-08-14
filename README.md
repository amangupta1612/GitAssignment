

****************GIT HUB API Automation***************************

Scenario 1 : Create and Verify repository
Step to be executed: - 
i) go to github user repo URI with valid access token
ii) create repository with name GitRepo_demo
iii) verify that repo is got created
pre-condition:
	User should have valid AccessToken id

Scenario 2: Star the Created repository
Step to be executed: - 
i) Goto user starred repo URI and add the repo to starred repository
ii) Verify if Added repository added to starred repository

Scenario 3: Get Repo details and verify that it is the same one you created 
Step to be executed: - 
i) fetch the details using GET method for the user repo URI
ii) Verify with the Repo name with name of repository we fetched the details.
