<?xml version='1.0' encoding='UTF-8'?>
<sentences>
	<query>
		<id>corp_contacts_list</id>
		<index>corp_contacts_list</index>
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
					        "range":{
					        	"corp_rc" : {"gte" : %s,"lte" : %s},
					        	"corp_edate" : {"gte" : "%s","lte" : "%s"}
					        }
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