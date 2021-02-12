
val redshiftQuery =
  "(SELECT id, lastUpdated FROM db.table " +
    "WHERE id IN ('" + Ids.mkString("','") + "'))"

// user, password to be got from AWS SecretManager
val redshift_df = spark.sqlContext.read
  .format("jdbc")
  .option("driver", "com.amazon.redshift.jdbc42.Driver")
  .option("url", dbURL)
  .option("user", user)
  .option("password", password)
  .option("dbtable", redshiftQuery)
  .load()