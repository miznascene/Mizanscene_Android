package com.example.mizansen.Adapters;

public class VideoAdapter {// extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

//    List<VideosDataModel> dataModels;
//
//    public VideoAdapter(List<VideosDataModel> _dataModels) {
//        dataModels = _dataModels;
//    }
//
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
//
//        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_channel, viewGroup, false);
//        return new ViewHolder(itemView);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int i) {
//
//        holder.id.setText(dataModels.get(i).id);
//        holder.title.setText(dataModels.get(i).title);
//        holder.description.setText(dataModels.get(i).description);
//    }
//
//    @Override
//    public int getItemCount() {
//        return dataModels.size();
//    }
//
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//
//        TextView id, description, title;
//
//        public ViewHolder(@NonNull View iv) {
//            super(iv);
//
//            id = iv.findViewById(R.id.adapter_channel_id);
//            title = iv.findViewById(R.id.adapter_channel_title);
//            description = iv.findViewById(R.id.adapter_channel_description);
//
//            iv.setOnClickListener(this);
//
//
//        }
//
//        @Override
//        public void onClick(View view) {
//            int i = getAdapterPosition();
//            Api_Activity.showVideo("videos/"+dataModels.get(i).id);
//        }
//    }

}
