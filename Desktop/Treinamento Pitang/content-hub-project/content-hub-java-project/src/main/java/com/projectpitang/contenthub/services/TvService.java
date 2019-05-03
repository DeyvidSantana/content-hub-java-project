package com.projectpitang.contenthub.services;

import com.projectpitang.contenthub.models.TV;
import com.projectpitang.contenthub.repository.TvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TvService {

    @Autowired
    private TvRepository tvRepository;

    public TvRepository getTvRepository() {
        return tvRepository;
    }

    public void setTvRepository(TvRepository tvRepository) {
        this.tvRepository = tvRepository;
    }

    public Page<TV> getAll(Pageable pageable){
        return this.tvRepository.findAll(pageable);
    }

    public List<TV> findTvByTitle(String title){

        List<TV> tvs = this.tvRepository.findByTitleLike(title);

        if(tvs.size() != 0){
            return tvs;
        } else {
            return null;
        }

    }

    public List<TV> findTvByLanguage(String language){

        List<TV> tvs = this.tvRepository.findByLanguageLike(language);

        if(tvs.size() != 0){
            return tvs;
        } else {
            return null;
        }

    }

    public List<TV> findTvByDate(String date){

        List<TV> tvs = this.tvRepository.findByReleaseYearLike(date);

        if(tvs.size() != 0){
            return tvs;
        } else {
            return null;
        }

    }

    public TV updateTv(Long id, TV tv){

        Optional<TV> tvToBeUpdated = this.tvRepository.findById(id);

        if(tvToBeUpdated != null){
            TV tvUpdated = new TV();
            tvUpdated.setId(tvToBeUpdated.get().getId());
            tvUpdated.setIdApi(tvToBeUpdated.get().getIdApi());
            tvUpdated.setTitle(tvToBeUpdated.get().getTitle());
            tvUpdated.setOverview(tvToBeUpdated.get().getOverview());
            tvUpdated.setOriginCountry(tvToBeUpdated.get().getOriginCountry());
            tvUpdated.setLanguage(tvToBeUpdated.get().getLanguage());
            tvUpdated.setReleaseYear(tvToBeUpdated.get().getReleaseYear());
            tvUpdated.setRuntime(tvToBeUpdated.get().getRuntime());

            this.tvRepository.save(tvUpdated);

            return tvUpdated;
        } else {
            return null;
        }

    }

    public boolean deleteTv(Long id){

        boolean tvExists = this.tvRepository.existsById(id);
        if(tvExists){
            this.tvRepository.deleteById(id);
            return true;
        } else {
            return false;
        }

    }

}
