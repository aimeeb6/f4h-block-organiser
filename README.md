# Block Organiser

The file *MyApp* has 3 print methods that will write to blockinfo.text. The print methods will have a block listed and below it will display what forms that block is in. It will also display the live status of a form. The different methods print different lists of blocks 
  * `printAllBlockInfo(); `- this will print info on all blocks in the ltht-forms-definitions repo. 
  * `printBlocksWithNoUse();`- this will print info on blocks in the ltht-forms-definitions repo that are not used in any forms.
  * `printBlocksNotLive() ` - this will print info on blocks in the ltht-forms-definitions that are not used in any non-live forms. 
  
As these methods all write to the same text file you can only run one at a time and you should comment the others out. 

## Set up 
To run the code you have to add you repository directories to the *MyApp* file. This is done on line 9 and 10. Be sure to put the ltht-forms-definition in the f4hPathname variable and the forms4health-form-definitions pathname in the livePathname variable. 

## Notes
1. This software currently doesn't handle form versions so this means:
    * if any version of the form is live it will display all versions as live 
    * When a form is shown to have a block, this means that some VERSION of the form had this block. It may not be the most recent one. 
    
2. The re-naming of blocks on the F4H website actually creates a new block and leaves the old in the repository so you may get very similarly named blocks that seem incorrect. 
    * An example of this is `patient--personal-disclaimer` vs `patient-personal-disclaimer`
3. The live status of the form is based on the forms4health-form-definitions repo so for accuracy, you should have the production branch checked out. 
