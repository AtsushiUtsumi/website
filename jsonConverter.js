function jsonConvert(json) {
    var output = '';
    tables = json['tables'];
    for (table of tables) {
        output += table['tableName']+'\n';
    }
    return output;
}

function OnClickJsonConvert() {
    jsonInput = document.getElementById("jsonInput");
    try {
        json = JSON.parse(jsonInput.value)
        document.getElementById("jsonOutput").innerText = jsonConvert(json);
    } catch (error) {
        document.getElementById("jsonOutput").innerText = "JOSN形式ではありません";
    }
}
/*
{
    "databaseName": "hogeDb",
    "tables": [
        {
            "tableName": "tableA",
            "columns": [
                {"columnName": "columnA"},
                {"columnName": "columnB"}
            ]
        },
        {
            "tableName": "tableB",
            "columns": [
                {"columnName": "columnA"},
                {"columnName": "columnB"}
            ]
        },
        {
            "tableName": "tableC",
            "columns": [
                {"columnName": "columnA"},
                {"columnName": "columnB"}
            ]
        }
    ]
}
*/