package Core;

public enum Commands{	
	//COSTANTI ENUMERATIVE
			
			tableExistBook	("Check Tb Exist"	,		SearchFor.Book,			CommandsType.CHANGE,1,	0),
			tableExistLoans	("Check Tb Exist"	,		SearchFor.Prenotation,	CommandsType.CHANGE,1,	0),
			tableExistPerson("Check Tb Exist"	,		SearchFor.Account,		CommandsType.CHANGE,1,	0),

			BookREAD 		("Book Read			",		SearchFor.Book,			CommandsType.READ	,1,0), 
			BookUPDATE 		("Book Update		",		SearchFor.Book,			CommandsType.CHANGE	,2,0),
			BookDELETE		("Book Delete		",		SearchFor.Book,			CommandsType.CHANGE	,3,0),
			
			PrenotationREAD	("Prenotation read	",		SearchFor.Prenotation,	CommandsType.READ	,4,0),
			Prenotation		("Prenotation new	",		SearchFor.Prenotation,	CommandsType.CHANGE	,4,0),
			BookGet			("Prenotation Get	",		SearchFor.Prenotation,	CommandsType.CHANGE	,4,0),
			BookGiveback	("Prenotation Give back	",	SearchFor.Prenotation,	CommandsType.CHANGE	,4,0),
			
			UserREAD 		("User Read			",		SearchFor.Account,		CommandsType.READ	,1,	0), 
			UserUPDATE 		("User Update		",		SearchFor.Account,		CommandsType.CHANGE	,2,	0),
			UserDELETE		("User Delete		",		SearchFor.Account,		CommandsType.CHANGE	,3,	0),		
		
			UserLOGIN 		("User Login"		,		SearchFor.Account,		CommandsType.READ,1,	0), 
			UserLOGOUT 		("User Logout"		,		SearchFor.Account,		CommandsType.READ,1,	0),
	// TEST
			DBExist 		("Check DB Exist"	,		SearchFor.SystemPar,	CommandsType.TEST,1,	0),
			//tableExistBook("Check Tb Exist"	,		SearchFor.SystemPar,	CommandsType.TEST,1,	0),
			//tableExistLoans("Check Tb Exist"	,		SearchFor.SystemPar,	CommandsType.TEST,1,	0),
			//tableExistPerson("Check Tb Exist"	,		SearchFor.SystemPar,	CommandsType.TEST,1,	0),
			//Test1			("Test 1"			,		SearchFor.SystemPar,	CommandsType.TEST,1,	0),
			//Test2			("Test 2"			,		SearchFor.SystemPar,	CommandsType.TEST,1,	0),
			//Test3			("Test 3"			,		SearchFor.SystemPar,	CommandsType.TEST,1,	0),
	// Connection Stub / Skeleton
			ConnSTOP		("StopConnection"	,		SearchFor.SystemPar,	CommandsType.CONNECTION,4,	0), 
			ConnTEST		("TestConnection"	,		SearchFor.SystemPar,	CommandsType.CONNECTION,10,0);		
	//campi
			public 	String 			Den;
			private SearchFor 		Target;
			private CommandsType 	CmdType;
			public 	int 			Priority;
			public 	int 			Records;					
	//costruttori
			private Commands (	String 	Den, SearchFor Tar,CommandsType CType,int pri,int rec){
			this.Den = 					Den;			
			this.setTarget(Tar);			
			this.setCommandsType(		CType);
			this.Priority = 			pri;
			this.Records=				rec;
			}
	// SET GET Methods		
			public CommandsType 		getCommandType() {
				return CmdType;
			}
			public void 				setCommandsType(CommandsType commandType) {
				CmdType = commandType;
			}
			public SearchFor 			getTarget() {
				return Target;
			}
			public void 				setTarget(SearchFor target) {
				Target = target;
			}
	};	
