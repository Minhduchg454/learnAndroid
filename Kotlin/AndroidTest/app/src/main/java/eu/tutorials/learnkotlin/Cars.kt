package eu.tutorials.learnkotlin

    class Cars {
        private var brand: String
        private var model: String
        private var year: Int

        constructor(){
            brand="No brand"
            model="No model"
            year=2024
        }
        constructor(brand: String, model: String, year: Int){
            this.model=model
            this.year=year
            this.brand=brand
        }

        fun getModel() =model

        fun maxSpeed (max: Int){
            println("Max speed is $max")
        }



    }