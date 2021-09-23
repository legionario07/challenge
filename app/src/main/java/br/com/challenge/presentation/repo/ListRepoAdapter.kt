package br.com.challenge.presentation.repo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.challenge.R
import br.com.challenge.data.entity.RepositoryEntity
import com.bumptech.glide.Glide

class ListRepoAdapter(private var listRepo: MutableList<RepositoryEntity>) :
    RecyclerView.Adapter<ListRepoAdapter.ListRepoHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListRepoHolder =
        ListRepoHolder.create(parent)

    override fun onBindViewHolder(holder: ListRepoHolder, position: Int) {
        holder.bind(listRepo)
    }

    fun updateList(listRepo: List<RepositoryEntity>) {
        this.listRepo.addAll(listRepo)
    }

    override fun getItemCount(): Int = listRepo.size

    class ListRepoHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(listItems: List<RepositoryEntity>) {
            with(itemView) {
                val repositoryEntity = listItems[layoutPosition]

                val textViewNameRepo = findViewById<TextView>(R.id.textview_name_repo)
                val textViewNameAuthor = findViewById<TextView>(R.id.textview_name_author)
                val textViewNumbersForks = findViewById<TextView>(R.id.textview_numbers_forks)
                val textViewNumbersStars = findViewById<TextView>(R.id.textview_numbers_stars)
                val imgAvatar = findViewById<ImageButton>(R.id.image_avatar)

                textViewNameRepo.text = repositoryEntity.nameRepository
                textViewNameAuthor.text = repositoryEntity.nameAuthor
                textViewNumbersForks.text = repositoryEntity.countForks.toString()

                textViewNumbersStars.text = repositoryEntity.countStars.toString()

                Glide.with(context)
                    .load(repositoryEntity.photoUser)
                    .into(imgAvatar);
            }
        }

        companion object {
            fun create(parent: ViewGroup): ListRepoHolder {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_repo, parent, false)
                return ListRepoHolder(view)
            }
        }
    }
}