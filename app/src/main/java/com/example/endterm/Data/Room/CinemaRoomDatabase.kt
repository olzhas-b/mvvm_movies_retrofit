package com.example.endterm.Data.Room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.endterm.Data.Models.Cinema.Cinema
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Database(entities = [Cinema::class], version = 2, exportSchema = false)
abstract class CinemaRoomDatabase: RoomDatabase() {
    abstract fun cinemaDao() : CinemaDao

    companion object {
        @Volatile
        private var INSTANCE: CinemaRoomDatabase? = null

        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CinemaRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CinemaRoomDatabase::class.java,
                    "cinema_database"
                )
                    .fallbackToDestructiveMigration()
                    .addCallback(CinemaDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                return instance
            }
        }

    }
    private class CinemaDatabaseCallback(
        private val scope: CoroutineScope
    ): RoomDatabase.Callback() {
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.cinemaDao())
                }
            }
        }

        suspend fun populateDatabase(cinemaDao: CinemaDao) {
            cinemaDao.deleteAll()

            var cinema = Cinema(
                1,
                "Кинотеатр Arsenal",
                "г. Нур-Султан, ул. Ы. Алтынсарина, 4",
                "8-701-520-59-78",
                "/i/files/media/arsenal-astana.jpg?h=300",
                51.1275005,
                71.4374077
            )
            cinemaDao.insert(cinema)
            cinema = Cinema(
                2,
                "Kinopark 6 Keruencity Astana",
                "г. Нур-Султан, ТЦ «KeruenCity Astana», 3 эт., Кургальджинское шоссе, 1",
                "+7 7172 790 999",
                "/i/files/media/kinopark-6-keruencity-astana.jpg?h=300",
                51.1454644,
                71.41372597

            )
            cinemaDao.insert(cinema)

            cinema = Cinema(
                3,
                "Kinopark 8 IMAX Saryarka",
                " г. Нур-Султан, пр. Туран, 24, ТЦ «Сары-арка», 3 этаж",
                "8 (7172) 79–09–99",
                "/i/files/media/kinopark-8-imax-saryarka.jpg?h=300",
                51.1384015,
                71.409995
            )
            cinemaDao.insert(cinema)
            cinema = Cinema(
                4,
                "Bekmambetov Cinema",
                "г. Алматы пр. Абая, 109, МФК «Globus»",
                "8(727) 356-98-78",
                "/i/files/media/bekmambetov-cinema.jpg?h=300",
                43.240263,
                76.905654

            )
            cinemaDao.insert(cinema)
            cinema = Cinema (
                5,
                "Киноцентр Арман (ТРЦ Asia Park)",
                "г. Алматы, пр. Райымбека 514а, уг. ул. Саина, Торгово-развлекательный центр «Asia Park»",
                "+7 727 343 51 00, +7 727 343 51 11",
                "/i/files/media/kinocentr-arman-trc-asia-park.jpg?h=300",
                51.1280624,
                71.41163344
            )

            cinemaDao.insert(cinema)
            cinema = Cinema (
                6,
                "LUMIERA Cinema",
                "г. Алматы, пр. Абылай хана, 62, «Арбат»",
                "+7 727 222 23 23, +7 707 782 82 11",
                "/i/files/media/kinoteatr-lumiera-almaty-0.jpg?h=300",
                43.262118,
                76.941373
            )

            cinemaDao.insert(cinema)
            cinema = Cinema (
                7,
                "CINEMAX Dostyk Multiplex / Dolby Atmos 3D",
                "г. Алматы, Самал-2, пр. Достык 111, уг. ул. Жолдасбекова, ТРЦ Dostyk Plaza",
                "+7 727 222 00 77, +7 727 225 39 01, +7 701 026 73 69",
                "/i/files/media/cinemax-dostyk-plaza2.jpg?h=300",
                43.233015,
                76.955765

            )
            cinemaDao.insert(cinema)
            cinema = Cinema(
                8,
                "Chaplin Mega Alma-Ata",
                "г. Алматы, Розыбакиева, 263, ТРЦ MEGA Alma-Ata, 2 этаж",
                "+7 747 174 06 07",
                "/i/files/media/chaplin-mega-almaty.jpg?h=300"

            )
            cinemaDao.insert(cinema)
            cinema = Cinema(
                9,
                "Сhaplin-mega-silkway",
                "г. Нур-Султан, ТРЦ MEGA Silk Way (территория EXPO), 2 этаж, Chaplin MEGA Silk Way",
                "+7 747 174 00 89",
                "/i/files/media/chaplin-mega-silkway--astana.jpg?h=300",
                43.304039,
                76.929475
            )
            cinemaDao.insert(cinema)
            cinema = Cinema(
                10,
                "Арман 3D (Азия Парк)",
                "г.Нур-Султан, пр. Кабанбай Батыра, 21 ТРЦ «Азия парк»",
                "+7 7172 97 87 95",
                "/i/files/media/arman-3d-aziya-park-astana.jpg?h=300",
                43.304039,
                76.929475
            )
            cinemaDao.insert(cinema)

            cinemaDao.insert(cinema)
            cinema = Cinema (
                11,
                "ТТИ «Алатау» 3D (г. Алматы)",
                "г. Алматы, микрорайон Нұркент, 6.",
                "8 (727) 398–85–36, 8 (727) 224–89–77",
                "/i/files/media/tti-alatau-3d.jpg?h=300",
                43.228496,
                76.857868
            )
            cinema = Cinema (
                12,
            "Кинотеатр Kinoplexx Sary Arka 3D (г. Алматы)",
            "г. Алматы, ул. Алтынсарина, 24",
            "8 727 277 0038",
            "/i/files/media/kinoplexx sary arka2019.jpg?h=300",
            43.228496,
            76.857868
            )
            cinemaDao.insert(cinema)
            cinema = Cinema (
                13,
                "Kinopark 11 (Есентай) IMAX (г. Алматы)",
                "г. Алматы, пр. Аль-Фараби, 77/8, ТЦ «Есентай-Молл»",
                "+7-701-762-45-11",
                "/i/files/media/kinopark-11-jesentaj-imax.jpg?h=300",
                43.218290,
                76.927638
            )
            cinemaDao.insert(cinema)
            cinema = Cinema (
                    14,
            "Кинотеатр «Цезарь 3D»",
            "г. Алматы, ул. Фурманова, 50, уг. ул. Гоголя",
            "+7 727 273-63-93",
            "/i/files/media/caesar2019.jpg?h=300",
            43.261020,
            76.946446
            )
            cinemaDao.insert(cinema)
            cinema = Cinema (
                15,
                "Kinopark 8 Moskva (г. Алматы)",
                "г. Алматы, пр. Абая, уг. Алтынсарина, ТРЦ MOSKVA Metropolitan",
                "+7 778 099 09 17, + 7727 331 76 99",
                "/i/files/media/kinopark-8-moskva.jpg?h=300",
                43.226886,
                76.864135
            )
            cinemaDao.insert(cinema)

        }
    }
}