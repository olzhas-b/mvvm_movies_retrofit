//package com.example.endterm.Presentation.Cinema
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.ImageView
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.bumptech.glide.Glide
//import com.example.endterm.R
//import com.example.endterm.Utils.Constant
//
//
//
//class CinemaListAdapter( ) : RecyclerView.Adapter<CinemaListAdapter.CinemaViewHolder>() {
//
//
//
//    private val TAG = "CinemaListAdapter"
//    private val cinemaList = ArrayList<CinemaData>()
//
//    fun addItems(list: List<CinemaData>) {
//        cinemaList.addAll(list)
//        notifyDataSetChanged()
//    }
//
//    fun clearAll() {
//        cinemaList.clear()
//        notifyDataSetChanged()
//    }
//
//    fun getItem(position: Int) : CinemaData? {
//        return cinemaList[position]
//    }
//    inner class CinemaViewHolder(view: View): RecyclerView.ViewHolder(view) {
//        val tvTitle : TextView
//        val tvVoteAverage : TextView
//        val image : ImageView
//
//        init {
//            tvTitle = view.findViewById(R.id.title_TextView)
//            tvVoteAverage = view.findViewById(R.id.vote_average_TextView)
//            image = view.findViewById(R.id.imageView)
//        }
//
//        fun bind (cinema: CinemaData) {
//            tvTitle.text = cinema.title
//            tvVoteAverage.text = cinema.vote_average.toString()
//            val imageUrl = "${Constant.POSTER_CINEMA_BASE_URL}${cinema.poster}"
//
//            val imageUrl = "${Constant.BACKDROP_BASE_URL}${cinema.backdropImg}"
//            Glide.with(itemView.context)
//                .load(imageUrl)
//                .into(image)
//
//            Log.d("CinemaAdapter:", imageUrl + "   -----  " +  itemView.context.toString() + " -----  " + image.toString() + " ]]] ")
//        }
//
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CinemaViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.cinema_list, parent, false)
//        return CinemaViewHolder(view)
//    }
//
//    override fun getItemCount(): Int = cinemaList.size
//
//    override fun onBindViewHolder(holder: CinemaViewHolder, position: Int) {
//        val cinema = cinemaList[position]
//        holder.bind(cinema)
//    }
//
//}
