package com.example.tic_tac_toe.ui.home

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.gridlayout.widget.GridLayout
import com.example.tic_tac_toe.R
import com.example.tic_tac_toe.databinding.FragmentNotificationsBinding
import com.example.tic_tac_toe.databinding.FragmentStartGameBinding
import com.example.tic_tac_toe.ui.BaseFragment
import com.example.tic_tac_toe.ui.notifications.NotificationsViewModel
import com.example.tic_tac_toe.ui.utils.ResourceUtils
import com.google.android.material.imageview.ShapeableImageView
import dagger.hilt.android.AndroidEntryPoint
import java.util.ArrayList

@AndroidEntryPoint
class StartGameFragment : BaseFragment<StartGameViewModel>(StartGameViewModel::class.java) {

    private var _binding: FragmentStartGameBinding? = null

    private val binding get() = _binding!!

    private val circleLinearLayoutMap = mutableMapOf<String, LinearLayout>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStartGameBinding.inflate(inflater, container, false)
        createAvatarsCardToGridLayout()
        createAvatarsCardToGridLayout2()
        val root: View = binding.root

        return root
    }

    private fun createAvatarsCardToGridLayout(){
        var drawableList = listOf(
            Pair(R.drawable.player_icon_choice_cr_b, "cross_blue"),
            Pair(R.drawable.player_icon_choice_cr_g, "cross_green"),
            Pair(R.drawable.player_icon_choice_cr_o, "cross_orange"),
            Pair(R.drawable.player_icon_choice_cr_pi, "cross_pink"),
            Pair(R.drawable.player_icon_choice_cr_pu, "cross_purple"),
            Pair(R.drawable.player_icon_choice_cr_r, "cross_red"),
            Pair(R.drawable.player_icon_choice_cr_w, "cross_white"),
            Pair(R.drawable.player_icon_choice_cr_y, "cross_yellow"),
        )

        circleLinearLayoutMap.clear()

        for (index in drawableList.indices){
            var cardView: CardView = LayoutInflater.from(context).inflate(
                R.layout.item_icon,
                binding.gridLayoutPlayer1AvatarList,
                false
            ) as CardView

            cardView.findViewWithTag<ShapeableImageView>("ShapeableImageView")?.setImageResource(drawableList[index].first)
            val myGLP = GridLayout.LayoutParams()
            val rowSpec: GridLayout.Spec = GridLayout.spec(index / 4, 1, 0.25f)
            val colSpec: GridLayout.Spec = GridLayout.spec(index % 4, 1, 0.25f)

            circleLinearLayoutMap[drawableList[index].second] = findViewWithTagRecursively(cardView, "layoutWithCircle")[0] as LinearLayout

            myGLP.rowSpec = rowSpec
            myGLP.columnSpec = colSpec
            binding.gridLayoutPlayer1AvatarList.addView(cardView, myGLP)
            cardView.setOnClickListener {
                viewModel.player1.value!!.drawable = drawableList[index].second

            }
        }
    }

    private fun createAvatarsCardToGridLayout2(){
        var drawableList = listOf(
            Pair(R.drawable.player_icon_choice_ci_b, "circle_blue"),
            Pair(R.drawable.player_icon_choice_ci_g, "circle_green"),
            Pair(R.drawable.player_icon_choice_ci_o, "circle_orange"),
            Pair(R.drawable.player_icon_choice_ci_pi, "circle_pink"),
            Pair(R.drawable.player_icon_choice_ci_pu, "circle_purple"),
            Pair(R.drawable.player_icon_choice_ci_r, "circle_red"),
            Pair(R.drawable.player_icon_choice_ci_w, "circle_white"),
            Pair(R.drawable.player_icon_choice_ci_y, "circle_yellow"),
        )

        circleLinearLayoutMap.clear()

        for (index in drawableList.indices){
            var cardView: CardView = LayoutInflater.from(context).inflate(
                R.layout.item_icon,
                binding.gridLayoutPlayer2AvatarList,
                false
            ) as CardView

            cardView.findViewWithTag<ShapeableImageView>("ShapeableImageView")?.setImageResource(drawableList[index].first)
            val myGLP = GridLayout.LayoutParams()
            val rowSpec: GridLayout.Spec = GridLayout.spec(index / 4, 1, 0.25f)
            val colSpec: GridLayout.Spec = GridLayout.spec(index % 4, 1, 0.25f)

            circleLinearLayoutMap[drawableList[index].second] = findViewWithTagRecursively(cardView, "layoutWithCircle")[0] as LinearLayout

            myGLP.rowSpec = rowSpec
            myGLP.columnSpec = colSpec
            binding.gridLayoutPlayer2AvatarList.addView(cardView, myGLP)
            cardView.setOnClickListener {
                viewModel.player2.value!!.drawable = drawableList[index].second

            }
        }
    }

    fun findViewWithTagRecursively(root: ViewGroup, tag: Any): List<View> {
        val allViews: MutableList<View> = ArrayList()
        val childCount = root.childCount
        for (i in 0 until childCount) {
            val childView = root.getChildAt(i)
            if (childView is ViewGroup) {
                allViews.addAll(findViewWithTagRecursively(childView, tag)!!)
            }
//            else {
            val tagView = childView.tag
            if (tagView != null && tagView == tag) allViews.add(childView)
//            }
        }
        return allViews
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = this.viewModel
        binding.lifecycleOwner = this
    }


}