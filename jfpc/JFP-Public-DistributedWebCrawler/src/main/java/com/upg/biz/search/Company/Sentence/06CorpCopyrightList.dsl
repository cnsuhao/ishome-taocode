<?xml version='1.0' encoding='UTF-8'?>
<sentences>
	<query>
		<id>corp_copyright_list</id>
		<index>corp_copyright_list</index>
		<dsl>
			<![CDATA[
			{
			    "query": {
			        "filtered":{
				        "query": {
					        "bool":{
						        "must":[
						        	{"match" : {"full_name" : "%s"}}
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