<?xml version='1.0' encoding='UTF-8'?>
<sentences>
	<query>
		<id>corp_director_list</id>
		<index>corp_director_list</index>
		<dsl>
			<![CDATA[
			{
			    "query": {
			        "filtered":{
				        "query": {
					        "bool":{
						        "must":[
						        	{"match" : {"name" : "%s"}}
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