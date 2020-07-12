## How to Contribute

### Raising an issue:
 We will be raising issues ourseleves for so that you can contribute directly to it. But as this is an Open Source project and we would be happy to see contributors who report bugs, errors and file feature requests by submitting pull requests as well.
 This project adheres to the Contributor Covenant code of conduct.
 By participating, you are expected to uphold this code style.

### Branch Policy 

#### Sending pull requests:

Go to the repository on GitHub at [https://github.com/VBSquad/Androiders-Buddy](https://github.com/VBSquad/Androiders-Buddy)
Click the “Fork” button at the top right.

You’ll now have your copy of the original Androiders-Buddy repository in your GitHub account.

Open a terminal/shell.

`$ git clone https://github.com/username/Androiders-Buddy`

where 'username' is your username.

You’ll now have a local copy of your version of the original  Androiders-Buddy repository.

#### Change into that project directory (Androiders-Buddy):

`$ cd Androiders-Buddy`

#### Add a connection to the original owner’s repository.

`$ git remote add upstream https://github.com/VBSquad/Androiders-Buddy`

#### To check this remote add set up:

`$ git remote -v`

#### Make/Add changes to files.

Make/Add a java file by implementing that concept and then
`git add` and `git commit` those changes

`git push` them back to GitHub. These will go to your version of the repository.

#### Now Create a PR (Pull Request)
Go to your version of the repository on GitHub.

Click the “New pull request” button at the top.

Note that Androiders-Buddy's repository will be on the left and your repository will be on the right.

Click the green button “Create pull request”. Give a succinct and informative title, in the comment field give a short explanation of the changes and click the green button “Create pull request” again.

#### Pulling others’ changes
Before you make further changes to the repository, you should check that your version is up to date relative to Androiders-Buddy’s version.

Go into the directory for the project and type:

`$ git checkout origin master`
`$ git pull upstream master --rebase`

This will pull down and merge all of the changes that have been made in the original Androiders-Buddy.

Now push them back to your Github repository.

`$ git push origin master`
