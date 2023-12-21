package com.example.dbtest.database

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Task::class, Course::class, Building::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase(){
    abstract fun taskDao(): TaskDao
    abstract fun courseDao(): CourseDao
    abstract fun buildingDao(): BuildingDao
    companion object {
        private class DatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.buildingDao())
                    }
                }
            }
            private fun initBuilding():List<Building>{
                var b0 = Building("AAS","138 Mountfort Street")
                var b1 = Building("AGG","925 Commonwealth Avenue")
                var b2 = Building("ASC","300 Babcock Street")
                var b3 = Building("BRB","5 Cummington Mall")
                var b4 = Building("BSC","2 Cummington Mall")
                var b5 = Building("CAS","685 Commonwealth Avenue")
                var b6 = Building("CDS","665 Commonwealth Avenue")
                var b7 = Building("CFA","855 Commonwealth Avenue")
                var b8 = Building("CGS","871 Commonwealth Avenue")
                var b9 = Building("CLN","900 Commonwealth Avenue")
                var b10 = Building("CNS","677 Beacon Street")
                var b11 = Building("COM","640 Commonwealth Avenue")
                var b12 = Building("CRW","619 Memorial Dr, Cambridge, MA 02139")
                var b13 = Building("CSE","285 Babcock Street")
                var b14 = Building("EGL","236 Bay State Road")
                var b15 = Building("EIB","143 Bay State Road")
                var b16 = Building("EIL","285 Babcock Street")
                var b17 = Building("EMA","730 Commonwealth Ave, Boston, MA 02215")
                var b18 = Building("EMB","15 St. Mary’s Street")
                var b19 = Building("ENG","112 Cummington Mall")
                var b20 = Building("EOP","890 Commonwealth Avenue")
                var b21 = Building("EPC","750 Commonwealth Avenue, Boston, MA 02215")
                var b22 = Building("ERA","48 Cummington Mall")
                var b23 = Building("ERB","44 Cummington Mall")
                var b24 = Building("FAB","180 Riverway, Boston, MA 02215")
                var b25 = Building("FCB","25 Pilgrim Road")
                var b26 = Building("FCC","150 Riverway")
                var b27 = Building("FLR","808 Commonwealth Avenue")
                var b28 = Building("FOB","704 Commonwealth Avenue")
                var b29 = Building("FRC","915 Commonwealth Avenue")
                var b30 = Building("GDP","53 Bay State Road")
                var b31 = Building("GMS","72 East Concord Street")
                var b32 = Building("GRS","705 Commonwealth Avenue")
                var b33 = Building("GSU","775 Commonwealth Avenue, Boston, MA 02215")
                var b34 = Building("HAR","595 Commonwealth Avenue")
                var b35 = Building("HAW","43 Hawes Street, Brookline")
                var b36 = Building("HIS","226 Bay State Road")
                var b37 = Building("IEC","888 Commonwealth Avenue")
                var b38 = Building("IRB","154 Bay State Road")
                var b39 = Building("IRC","152 Bay State Road")
                var b40 = Building("JSC","147 Bay State Road")
                var b41 = Building("KCB","565 Commonwealth Avenue")
                var b42 = Building("KHC","91 Bay State Road")
                var b43 = Building("LAW","765 Commonwealth Avenue")
                var b44 = Building("LEV","233 Bay State Road")
                var b45 = Building("LNG","718 Commonwealth Avenue")
                var b46 = Building("LSE","24 Cummington Mall")
                var b47 = Building("MCH","735 Commonwealth Avenue")
                var b48 = Building("MCS","111 Cummington Mall")
                var b49 = Building("MED","715 Albany Street")
                var b50 = Building("MET","1010 Commonwealth Avenue")
                var b51 = Building("MOR","602 Commonwealth Avenue")
                var b52 = Building("MUG","771 Commonwealth Avenue")
                var b53 = Building("PDP","915 Commonwealth Avenue")
                val bList = listOf(b0, b1, b2, b3, b4, b5, b6, b7, b8,
                    b9, b10, b11, b12, b13, b14, b15, b16, b17, b18,
                    b19, b20, b21, b22, b23, b24, b25, b26, b27, b28,
                    b29, b30, b31, b32, b33, b34, b35, b36, b37, b38,
                    b39, b40, b41, b42, b43, b44, b45, b46, b47, b48,
                    b49, b50, b51, b52, b53)
                return bList
            }
            suspend fun populateDatabase(buildingDao: BuildingDao) {
                val bList = initBuilding()
                for(b in bList){
                    buildingDao.addBuilding(b)
                }

            }
        }
        @Volatile
        private var INSTANCE: TaskDatabase? = null
        fun getDatabase(context: Context): TaskDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TaskDatabase::class.java,
                    "task_database"
                ).addCallback(DatabaseCallback(CoroutineScope(Dispatchers.IO))).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}