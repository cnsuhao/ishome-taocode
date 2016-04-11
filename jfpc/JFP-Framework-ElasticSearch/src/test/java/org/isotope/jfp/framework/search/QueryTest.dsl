<?xml version='1.0' encoding='UTF-8'?>
<sentences>
	<query>
		<id>test</id>
		<index>corp_base_list</index>
		<dsl>
			<![CDATA[
			{
			    "query": {
			        "filtered":{
				        "query": {
					        "bool":{
						        "must":[
						        	{"match" : {"corp_name" : "%s"}},
						        	{"match" : {"res_date" : "%s"}}
						        ]
					        }
					    },
					    "filter":{
					        "range":{
					        	"corp_rc" : {"gte" : 10,"lte" : 100},
					        	"corp_edate" : {"gte" : "1999-04-07","lte" : "2015-03-11"}
					        }
					    }
			        }
			    },
			    "from":10,
			    "size":15
			}
			]]>
		</dsl>
	</query>
</sentences>