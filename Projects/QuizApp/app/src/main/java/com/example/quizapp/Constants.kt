package com.example.quizapp

object Constants {


    var name = "NAME"
    var name2 = "NAME2"
    var score = "Score"


    fun getquestion(): ArrayList<DataModel> {

        var arrayList: ArrayList<DataModel> = ArrayList()

        arrayList.add(
            DataModel(
                0,
                "What is the only food that cannot go bad?",
                4,
                "Dark chocolate",
                "Peanut butter",
                "Canned tuna",
                "Honey"
            )
        )

        arrayList.add(
            DataModel(
                1, "What’s the heaviest organ in the human body?", 2, "Brain", "Liver",
                "Skin", "Heart"
            )
        )

        arrayList.add(
            DataModel(
                2, "What is the highest-grossing video game franchise to date?",
                2, "Mario", "Pokemon", "Call of Duty", "Street Fighter"
            )
        )

        arrayList.add(
            DataModel(
                3,
                "What are the two highest-grossing films of all time?",
                2,
                "Avengers: End Game and Star Wars: Episode VII – The Force Awakens",
                "Avatar and Avengers: End Game",
                "Avengers: Infinity War and Titanic",
                "Furious 7 and Avatar"
            )
        )

        arrayList.add(
            DataModel(
                4, "What sport has been played on the moon?", 3,
                "Frisbee", "Soccer", "Golf", "Bocce ball"
            )
        )

        arrayList.add(
            DataModel(
                5, "Which sea creature has three hearts?", 4,
                "Shark", "Jellyfish", "Stingray", "Octopus"
            )
        )

        arrayList.add(
            DataModel(
                6, "What was the very first Pixar movie?", 4,
                "Wall-E", "Monsters, Inc.", "A Bug’s Life", "Toy Story"
            )
        )

        arrayList.add(
            DataModel(
                7, "What is the smallest country in the world?", 2,
                "Luxembourg", "The Vatican City", "Palau", "San Marino"
            )
        )

        arrayList.add(
            DataModel(
                8, "What is the hardest natural substance in the world?", 4,
                "Granite", "Marble", "Iron", "Diamond"
            )
        )

        arrayList.add(
            DataModel(
                9,
                "Which “Wonders Of The World” is still in existence?",
                2,
                "Temple of Artemis",
                " Pyramids of Giza",
                " Hanging Gardens of Babylon",
                "  Statue of Zeus"
            )
        )


        return arrayList

    }
}