package com.example.myapplication.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.ResultsListItemSmallBinding
import com.example.myapplication.model.Club
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.util.concurrent.ThreadLocalRandom

class ResultsAdapter(
    private val dataset: MutableList<Club>
) : RecyclerView.Adapter<ResultsAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ResultsListItemSmallBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ResultsAdapter.ItemViewHolder {
        val binding =
            ResultsListItemSmallBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val item = dataset[position]

        val firstHalf = dataset.subList(0, dataset.size / 2)
        val secondHalf = dataset.subList(dataset.size / 2, dataset.size)

        val randomClubGroup1 = firstHalf.random()
        val randomClubGroup2 = secondHalf.random()

        holder.binding.tvGroupOne.text = randomClubGroup1.name
        holder.binding.tvGroupTwo.text = randomClubGroup2.name
        holder.binding.tvSport.text = item.sport

        val generatedDate = generateRandomDateWithDistribution(30)
        val generatedTime = if (generatedDate == generateCurrentDate()) {
            randomTime()
        } else {
            "//"
        }
        holder.binding.tvTime.text = generatedTime

        val generatedDatePlus = if (generatedDate == generateCurrentDate()) {
            ("HEUTE, " + generatedDate)
        } else {
            (generatedDate)
        }
        holder.binding.tvDate.text = generatedDatePlus

        when (holder.binding.tvSport.text) {
            "FUSSBALL" -> holder.binding.tvGroupOneGoals.text = randomGoals()
            "BADMINTON" -> holder.binding.tvGroupOneGoals.text = randomGoals()
            "SQUASH" -> holder.binding.tvGroupOneGoals.text = randomGoalsSquash()
            "TISCHTENNIS" -> holder.binding.tvGroupOneGoals.text = randomGoals()
            "TENNIS" -> holder.binding.tvGroupOneGoals.text = randomGoals()
            "HOCKEY" -> holder.binding.tvGroupOneGoals.text = randomGoals()
            "CRICKET" -> holder.binding.tvGroupOneGoals.text = randomCricket()
            "HANDBALL" -> holder.binding.tvGroupOneGoals.text = randomHandball()
        }
        when (holder.binding.tvSport.text) {
            "FUSSBALL" -> holder.binding.tvGroupTwoGoals.text = randomGoals()
            "BADMINTON" -> holder.binding.tvGroupTwoGoals.text = randomGoals()
            "SQUASH" -> holder.binding.tvGroupTwoGoals.text = randomGoalsSquash()
            "TISCHTENNIS" -> holder.binding.tvGroupTwoGoals.text = randomGoals()
            "TENNIS" -> holder.binding.tvGroupTwoGoals.text = randomGoals()
            "HOCKEY" -> holder.binding.tvGroupTwoGoals.text = randomGoals()
            "CRICKET" -> holder.binding.tvGroupTwoGoals.text = ""
            "HANDBALL" -> holder.binding.tvGroupTwoGoals.text = randomHandball()
        }

        holder.binding.grouponepic.setImageResource(randomPicture())
        holder.binding.grouptwopic.setImageResource(randomPicture())
        holder.binding.tvLeague.text = randomLiga()
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun randomTime(): String {
        val randomTime = listOf<String>(
            "11",
            "17",
            "19",
            "22",
            "31",
            "42",
            "45+2",
            "48",
            "52",
            "57",
            "63",
            "67",
            "71",
            "75",
            "88",
            "90+2",
            "90+4"
        )
        return randomTime.random()
    }

    fun randomGoals(): String {
        val randomGoals = listOf<String>("0", "0", "0", "0", "1", "1", "1", "2", "3", "4")
        return randomGoals.random()
    }
    fun randomGoalsSquash(): String {
        val randomGoals = listOf<String>(
            "7",
            "8",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21"
        )
        return randomGoals.random()
    }

    fun randomCricket(): String {
        val randomGoals = listOf<String>(
            "231/1",
            "219/3",
            "119/4",
            "118/2",
            "167/7",
            "122/2",
            "145/3",
            "212/2",
            "223/1",
            "198/4"
        )
        return randomGoals.random()
    }

    fun randomHandball(): String {
        val randomGoals = listOf<String>(
            "12",
            "13",
            "24",
            "32",
            "27",
            "42",
            "37",
            "42",
            "51",
            "14",
            "39",
            "32",
            "27",
            "24",
            "32"
        )
        return randomGoals.random()
    }


    fun randomPicture(): Int {
        var pictures = listOf<Int>(
            R.drawable.achievement_1,
            R.drawable.award_1,
            R.drawable.download,
            R.drawable.download__1_
        )
        return pictures.random()
    }

    fun randomLiga(): String {
        var ligen = listOf<String>(
            "1ste Liga",
            "2te Liga",
            "3te Liga",
            "Freunschaftsspiel",
            "4te Liga",
            "Bundesliga",
            "Landesspiel"
        )
        return ligen.random()
    }

    fun generateRandomDateWithDistribution(maxDaysInPast: Long): String {
        val today = LocalDate.now()
        val randomValue = ThreadLocalRandom.current().nextDouble()

        val generatedDate = if (randomValue < 1.0 / 3.0) {
            today
        } else {
            val randomDays = ThreadLocalRandom.current().nextLong(1, maxDaysInPast + 1)
            today.minus(randomDays, ChronoUnit.DAYS)
        }
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yy")
        return generatedDate.format(formatter)
    }

    fun generateCurrentDate(): String {
        val today = LocalDate.now()
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yy")
        return today.format(formatter)
    }

}