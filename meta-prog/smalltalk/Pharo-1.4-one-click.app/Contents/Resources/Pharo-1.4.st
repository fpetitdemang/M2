"Preparations"
MCCacheRepository instVarNamed: 'default' put: nil.
!
"load updates"
UpdateStreamer new beSilent; updateFromServer.
!
"output Version Number of stdout"
FileStream stdout 
		nextPutAll: '[version] ';
		nextPutAll: SystemVersion current major asString;
		nextPutAll: '.';
		nextPutAll: SystemVersion current minor asString;
		nextPutAll: ' #' ;
		nextPutAll: SystemVersion current highestUpdate asString;
		cr.!
"Clear Author"
Author reset.
!
"Clear Monticello Caches"
MCCacheRepository instVarNamed: 'default' put: nil.
MCFileBasedRepository flushAllCaches.
MCMethodDefinition shutDown.
MCDefinition clearInstances.
!
"Cleanup Smalltalk"
Smalltalk flushClassNameCache.
Smalltalk organization removeEmptyCategories.
Smalltalk allClassesAndTraitsDo: [ :each |
	each organization removeEmptyCategories; sortCategories.
	each class organization removeEmptyCategories; sortCategories ].
!
"Cleanup System Memory"
Smalltalk garbageCollect.
Symbol compactSymbolTable.
!

"Save and Quit in a separate thread allowing other deferred startup actions to complete"
"[ "
    Processor yield.
    SmalltalkImage current snapshot: true andQuit: true.
"] forkAt: Processor systemBackgroundPriority."
!
