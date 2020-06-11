# Block Organiser

The file *MyApp* has 3 print methods that will write to different text files. The print methods will have a block listed and below it will display what forms that block is in. It will also display the live status of a form. This is shown below. 

<p align="center" ><img width="500"  alt="Screenshot 2020-06-11 at 16 59 55" src="https://user-images.githubusercontent.com/56455547/84412167-c1d58a80-ac06-11ea-8570-3d22d33a9fb7.png"><p>


  * `printAllBlockInfo(); `- this will print info on all blocks in the ltht-forms-definitions repo to allBlockInfo.txt
  * `printBlocksWithNoUse();`- this will print info on blocks in the ltht-forms-definitions repo that are not used in any forms to BlocksWithNoUse.txt
  * `printBlocksNotLive() ` - this will print info on blocks in the ltht-forms-definitions that are not used in any non-live forms to blocksNotLive.txt 

## Set up 
To run the code you have to add you repository directories to the *MyApp* file. This is done on line 9 and 10. Be sure to put the ltht-forms-definition in the f4hPathname variable and the forms4health-form-definitions pathname in the livePathname variable. 

## Notes
1. This software currently doesn't handle form versions so this means:
    * if any version of the form is live it will display all versions as live 
    * A form will be mentioned if any version of it contains that block. 
    
2. The re-naming of blocks on the F4H website actually creates a new block and leaves the old in the repository so you may get very similarly named blocks that seem incorrect. 
    * An example of this is `patient--personal-disclaimer` vs `patient-personal-disclaimer`
3. The live status of the form is based on the forms4health-form-definitions repo so for accuracy, you should have the production branch checked out. 
