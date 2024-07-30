package com.rabiakambur.cookly.home.work

object NotificationMessages {
    private val messages = listOf(
        "🍝 If you're looking for more than just a recipe, you're in the right place!",
        "🍔 Hungry? Delicious recipes are waiting for you!",
        "🍕 The secret to making pizza: Lots of cheese and love!",
        "🍲 Ready to create wonders in the kitchen with these recipes?",
        "🍜 Cooking is an art, and you are the greatest chef!",
        "🍩 Solution to sweet cravings: Try our new dessert recipes!",
        "🥗 Get ready to meet healthy and delicious recipes!",
        "🍳 Make breakfast time joyful with these recipes waiting for you!",
        "🍗 Welcome to our kitchen full of chicken recipes!",
        "🍰 If you love making desserts, these recipes are just for you!",
        "🥘 Looking for inspiration for dinner? You're in the right place!",
        "🍜 Perfect recipes for ramen lovers are here!",
        "🥙 Make cooking more fun with quick and easy recipes!",
        "🍚 Tired of plain rice? Check out our new recipes!",
        "🍲 Soup recipes to warm you up this winter are here!",
        "🌮 Bring Mexican cuisine to your home, try our new recipes!",
        "🍛 Do you love Indian food? Then don't miss our recipes!",
        "🍞 Want to bake your own bread? Check out our recipes!",
        "🍆 Make healthy and delicious meals with vegetable recipes!",
        "🍔 Try our burger recipes and create your own burger!",
        "🍝 If you love Italian cuisine, don't miss our recipes!",
        "🥧 Brighten up your tea time with tart and cake recipes!",
        "🍲 Bored of classic recipes? Try different flavors!",
        "🍗 Having a barbecue party? Check out our recipes!",
        "🍰 Want to learn the secrets of making cakes? Look at our recipes!",
        "🍳 Brighten up your breakfast with our omelet recipes!"
    )

    fun getRandomNotificationMessage(): String {
        return messages.random()
    }
}