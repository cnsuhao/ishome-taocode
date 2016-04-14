<?xml version='1.0' encoding='UTF-8'?>
<sentences>
	<query>
		<id>corp_base_list2</id>
		<index>corp_base_list</index>
		<dsl>
			<![CDATA[
			{
			    "query": {
			        "filtered":{
				        "query": {
					        "bool":{
						        "must":[
						        	{"match" : {"corp_name" : "%s"}}
						        	%s
						        ]
					        }
					    },
					    "filter":{
					    	"and":[
					    		{
							        "range":{
							        	"corp_rc" : {"gte" : %s,"lte" : %s}
							        }
						        },
						        {
							        "range":{
							        	"corp_edate" : {"gte" : "%s","lte" : "%s"}
							        }
							    }
					    	]
					    }
			        }
			    },
			    "from":%s,
			    "size":%s
			}
			]]>
		</dsl>
	</query>
</sentences>